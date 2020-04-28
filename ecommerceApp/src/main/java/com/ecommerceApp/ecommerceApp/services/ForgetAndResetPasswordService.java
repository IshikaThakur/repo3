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
    public String resetPassword(PasswordDto passwordDto, String Token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(Token);
        String password = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();
        if (verificationToken.getEmail() == null) {
            return "http://localhost:8080/resetPassword?token=" + verificationToken.getToken()+ "expired";
        } else {
            if (!password.equals(confirmPassword)) {
                throw new PasswordNotMatchedException("password and confirm password doesn't matched");
            } else {
                String email = verificationToken.getEmail();
                System.out.println("just to check");
                Users users = userRepository.findByEmail(email);
                if (users.getEmail() == null) {
                    throw new UserNotFountException("user not found");
                } else {
                    try {
                        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                        simpleMailMessage.setSubject("Account security issue");
                        simpleMailMessage.setFrom("tanu.thakur0816@gmail.com");
                        simpleMailMessage.setText("To reset your password, please click on the Link given below :" + "" +
                                "\n http://localhost:8080/resetPassword?token=" + verificationToken.getToken());
                        simpleMailMessage.setTo(users.getEmail());
                        emailSenderService.sendEmail(simpleMailMessage);
                        String encodePassword = passwordEncoder.encode(password);
                        userRepository.updatePassword(encodePassword, users.getEmail());
                        verificationTokenRepository.deleteById(verificationToken.getId());
                    } catch (Exception ex) {
                        return " click the reset password link again";
                    }
                }
            }
            return "password changed successfully";
        }
    }
}


