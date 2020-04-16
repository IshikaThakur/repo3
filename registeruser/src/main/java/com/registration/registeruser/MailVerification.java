package com.registration.registeruser;


import com.registration.registeruser.Dao.TokenDao;
import com.registration.registeruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public class MailVerification {

    private JavaMailSender javaMailSender;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    public MailVerification(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    public void sendNotificaitoin(User user) throws MailException {
        System.out.println("Sending email...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("ishikathakur880@gmail.com");
        mail.setSubject("To verify account");
        String uu = tokenDao.getToken(user);
        mail.setText(uu);
        javaMailSender.send(mail);
        System.out.println("Email Sent!");
    }
}