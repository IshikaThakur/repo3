package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.Repositories.VerificationTokenRepository;
import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.entities.VerificationToken;
import com.ecommerceApp.ecommerceApp.exceptions.PasswordNotMatchedException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ForgetAndResetPasswordService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public String forgot_password(String email) {

        System.out.println(email);

        Users users = userRepository.findByEmail(email);

        System.out.println(users.getEmail());

        if (users.getEmail() != null) {
            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setCreatedDate(new Date());
            verificationToken.setExpiryDate(new Date());
            verificationToken.setToken(token);
            verificationToken.setEmail(email);


            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("Reset Your Password");
            simpleMailMessage.setFrom("tanu.thakur0816@gmail.com");

            verificationTokenRepository.save(verificationToken);
            simpleMailMessage.setTo(users.getEmail());
            simpleMailMessage.setText("To reset your password, please click on the Link given below :" + "" +
                    "\n http://localhost:8080/resetPassword/" + verificationToken.getToken());
            emailSenderService.sendEmail(simpleMailMessage);
        }
        return "check email to reset password";
    }

}