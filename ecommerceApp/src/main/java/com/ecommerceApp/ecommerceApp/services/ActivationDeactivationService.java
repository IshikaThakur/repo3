package com.ecommerceApp.ecommerceApp.services;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import java.util.Optional;

@Service
public class ActivationDeactivationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSenderService emailSenderService;

    public String ActivateUser(Long id, WebRequest request) {
        Optional<Users> user = userRepository.findById(id);
        String message;

        if (!user.isPresent()) {
            message = "no user is present of id = " + id + "";
        } else {
            Users saveUser = user.get();
            if (saveUser.isActive()) {
                message = "User is already active";
            } else {
                saveUser.setActive(true);
                userRepository.save(saveUser);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("Account Activation");
                simpleMailMessage.setText("Your account has been activated");
                simpleMailMessage.setTo(saveUser.getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                message = "User account has been activated";
            }
        }
        return "activated";
    }

    public String DeactivateUser(Long id, WebRequest request) {
        Optional<Users> user = userRepository.findById(id);
        String message;

        if (!user.isPresent()) {
            message = "no user is present of id = " + id + "";
        } else {
            Users saveUser = user.get();
            if (!saveUser.isActive()) {
                message = "User is inactive";
            } else {
                saveUser.setActive(false);
                userRepository.save(saveUser);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("Account De-activation");
                simpleMailMessage.setText("Your account has been deactivated");
                simpleMailMessage.setTo(saveUser.getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                message = "User acount has been Deactivated";
            }
        }
        return "de-activated";
    }

    public String ActivateUserAccount(Long id, WebRequest request) {
        Optional<Users> user = userRepository.findById(id);
        String text;
        if (!user.isPresent()) {
            text = "no user is present of such id=" + id + "";
        }
        else {
           Users saveUser=user.get();
           if(saveUser.isAccountNonLocked())
           {
               text="User account is already active";
           }
           else
           {
            saveUser.setAccountNonLocked(true);
            userRepository.save(saveUser);
               SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
               simpleMailMessage.setSubject("Account Activation");
               simpleMailMessage.setText("Your account has been activated");
               simpleMailMessage.setTo(saveUser.getEmail());
               emailSenderService.sendEmail(simpleMailMessage);
               text = "User account has been unlocked";
           }
           }
    return "Account Unlocked";
    }}


