package com.registration.registeruser.dao;



import com.registration.registeruser.Exception.TokenNotFoundException;
import com.registration.registeruser.MailService;
import com.registration.registeruser.entity.TokenGenerator;
import com.registration.registeruser.entity.User;
import com.registration.registeruser.repository.TokenRepository;
import com.registration.registeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;
@Service
public class TokenDao {

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailVerification;
    public String getToken(User user){
        TokenGenerator token = new TokenGenerator();
        String uu = UUID.randomUUID().toString();
        token.setRandomToken(uu);
        token.setTimeInMill(System.currentTimeMillis());
        token.setName(user.getUsername());
        tokenRepository.save(token);
        return uu;
    }

    public void verifyToken(String u) {
        TokenGenerator token1 = null;
        for (TokenGenerator token : tokenRepository.findAll()) {
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
                mailVerification.sendNotification(userRepository.findByUsername(token1.getName()));
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
