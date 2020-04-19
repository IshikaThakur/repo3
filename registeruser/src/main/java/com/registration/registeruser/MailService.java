package com.registration.registeruser;


import com.registration.registeruser.dao.TokenDao;
import com.registration.registeruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class MailService {

    @Autowired
    TokenDao tokenDao;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    public void  sendNotification(User user) throws MailException {
        System.out.println("Sending email for verification process...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("tanu.thakur0816@gmail.com");
        mail.setSubject("Account Verification==================================");
        String uu = tokenDao.getToken(user);
        mail.setText(uu);
        javaMailSender.send(mail);
        System.out.println("Email Sent!");
    }
}