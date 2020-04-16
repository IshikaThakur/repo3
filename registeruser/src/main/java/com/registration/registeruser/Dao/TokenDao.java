package com.registration.registeruser.Dao;



import com.registration.registeruser.Exception.TokenNotFoundException;
import com.registration.registeruser.MailVerification;
import com.registration.registeruser.entity.Token;
import com.registration.registeruser.entity.User;
import com.registration.registeruser.repository.TokenRepository;
import com.registration.registeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.UUID;
public class TokenDao {

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailVerification mailVerification;
    public String getToken(User user){
        Token token = new Token();
        String uu = UUID.randomUUID().toString();
        token.setRandomToken(uu);
        token.setTimeInMill(System.currentTimeMillis());
        token.setName(user.getUsername());
        tokenRepository.save(token);
        return uu;
    }

    public void verifyToken(String u) {
        Token token1 = null;
        for (Token token : tokenRepository.findAll()) {
            if (token.getRandomToken().equals(u)) {
                token1 = token;
            }
        }
        if (token1 == null)
        {
            throw new TokenNotFoundException("token is invalid");
        } else {
            if (token1.isExpired())
            {
                mailVerification.sendNotificaitoin(userRepository.findByUsername(token1.getName()));
                tokenRepository.delete(token1);
            } else {
                System.out.println("saving");
                User user = userRepository.findByUsername(token1.getName());
                user.setActive(true);
                System.out.println(user.getUsername() + " " + user.getActive());
                userRepository.save(user);
                tokenRepository.delete(token1);
            }
        }
    }
}
