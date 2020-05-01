package com.ecommerceApp.ecommerceApp;

import com.ecommerceApp.ecommerceApp.Repositories.UserAttemptsRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.UserAttempts;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.EmailSenderService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class CustomEventListener
{
    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    @Autowired
    UserRepository userRepository;

    @Lazy
    @Autowired
    EmailSenderService emailSenderService;
    @EventListener
    public void AuthenticationFailEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = event.getAuthentication().getPrincipal().toString();
        Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();
        int count = 0;
        for (UserAttempts userAttempts1 : userAttempts) {
            if (userAttempts1.getEmail().equals(username)) {
                if (userAttempts1.getAttempts() >= 2) {
                    Users user = userRepository.findByEmail(username);
                    user.setLocked(true);
                    user.setAccountNonLocked(false);
                    userRepository.save(user);

                    count++;
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setSubject("Account Locked");
                    simpleMailMessage.setFrom("tanu.thakur0816@gmail.com");
                    simpleMailMessage.setTo(user.getEmail());
                    simpleMailMessage.setText("Your account has been locked");
                    emailSenderService.sendEmail(simpleMailMessage);
                    throw new BadCredentialsException("Account locked!!!..............Invalid Login Attempts");
                }
             else {
                userAttempts1.setAttempts(userAttempts1.getAttempts() + 1);
                userAttemptsRepository.save(userAttempts1);
                count++;
            }
        }
    }
        if (count==0)
        {
            UserAttempts userAttempts1 = new UserAttempts();
            Users user = userRepository.findByEmail(username);
            userAttempts1.setEmail(user.getEmail());
            userAttempts1.setAttempts(1);
            userAttemptsRepository.save(userAttempts1);
        }
    }

    @EventListener
    public void AuthenticationPass(AuthenticationSuccessEvent event)
    {
        try {
            LinkedHashMap<String ,String > hashMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
            Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();


            for (UserAttempts userAttempts1 : userAttempts)
            {
                if (userAttempts1.getEmail().equals(hashMap.get("username")))
                {
                    userAttemptsRepository.deleteById(userAttempts1.getId());
                }
            }
        }
        catch (Exception e)
        {

        }
    }
}



