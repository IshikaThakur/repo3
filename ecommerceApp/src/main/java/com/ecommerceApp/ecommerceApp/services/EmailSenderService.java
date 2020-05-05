package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service("emailSenderService")
public class EmailSenderService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public  void sendEmail(SimpleMailMessage email)
    {
        javaMailSender.send(email);
    }

   public SimpleMailMessage getCustomerAwaitingActivationMail(String customerEmail, String confirmationToken)
   {
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setTo(customerEmail);
       mailMessage.setSubject("Complete Registration!");
       mailMessage.setFrom("tanu.thakur0816@gmail.com");
      mailMessage.setText("To confirm your account, please click here : "
              +"http://localhost:8080/register/confirm?token="+confirmationToken);

       return mailMessage;
   }

}

