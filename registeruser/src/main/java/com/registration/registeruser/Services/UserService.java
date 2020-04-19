package com.registration.registeruser.Services;

import com.registration.registeruser.dao.TokenDao;
import com.registration.registeruser.dto.AddressDto;
import com.registration.registeruser.Exception.UserNotFoundException;
import com.registration.registeruser.MailService;
import com.registration.registeruser.dto.CustomerDto;
import com.registration.registeruser.entity.Address;
import com.registration.registeruser.entity.Customer;
import com.registration.registeruser.entity.TokenGenerator;
import com.registration.registeruser.entity.User;
import com.registration.registeruser.repository.AddressRepository;
import com.registration.registeruser.repository.CustomerRepository;
import com.registration.registeruser.repository.TokenRepository;
import com.registration.registeruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    private JavaMailSender javaMailSender;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TokenDao tokenDao;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    MailService mailVerification;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    public UserService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void activateUser(Long id)
    {
        User user1 = null;
        String message;
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
        {
            user1 = user.get();
            if (user1.getActive()==true)
            {
                message = "user account is already activated";
            }
            else
            {
                user1.setActive(true);
                System.out.println("Sending email for account activation");
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getEmail());
                mail.setFrom("ishikathakur880@gmail.com");
                mail.setSubject("Regarding account activation");
                mail.setText("your account has been activated by admin you can now login");
                System.out.println("now starting");
                javaMailSender.send(mail);
                userRepository.save(user1);
                System.out.println("Email Sent!");
                message = "your account has been activated";
            }
        }
        else
        {
            throw new UserNotFoundException("user with this id is not present");
        }
        System.out.println("Id activated");
    }

    @Async
    public String  deActivateuser(Long id)
    {
        User user1 = null;
        String message = null;
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            user1 = user.get();
            if (user1.getActive()==false)
            {
                message = "user account is already deactivated";
            }
            else
            {
                user1.setActive(false);
                userRepository.save(user1);
                System.out.println("Sending email...");
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user1.getEmail());
                mail.setFrom("ishikathakur880@gmail.com");
                mail.setSubject("Regarding account deactivation");
                mail.setText("your account has been deactivated by admin you can not login now");
                javaMailSender.send(mail);
                System.out.println("Email Sent!");
                message = "your account has been activated";
            }
        }
        else
        {
            System.out.println("user with this id is not present");
            throw new RuntimeException();
        }
        return message;
    }

    public void forgotPassword(String email_id) {
        User user = userRepository.findByEmail(email_id);
        if (user == null) {
            System.out.println("no user found with this email id");
            throw new RuntimeException();
        } else {
            System.out.println("Sending email...");
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmail());
            mail.setFrom("ishikathakur880@gmail.com");
            mail.setSubject("Regarding forgot password");
            String uu = tokenDao.getToken(user);
            mail.setText(uu);
            javaMailSender.send(mail);
            System.out.println("Email Sent!");
        }
    }

    public void setPassword(String token_on_mail, String password) {
        TokenGenerator token1 = null;
        for (TokenGenerator token : tokenRepository.findAll()) {
            if (token.getRandomToken().equals(token_on_mail)) {
                token1 = token;
            }
        }
        if (token1 == null) {
            System.out.println("invalid token");
        } else {
            if (token1.isExpired()) {
                mailVerification.sendNotification(userRepository.findByUsername(token1.getName()));
                tokenRepository.delete(token1);
            } else {
                User user2 = userRepository.findByUsername(token1.getName());
                user2.setPassword(new BCryptPasswordEncoder().encode(password));
                userRepository.save(user2);
                tokenRepository.delete(token1);
            }

        }
    }
    public ResponseEntity<String> updateAddressById(String email, Long addressId, AddressDto addressDto) {
        Optional<Address> address = addressRepository.findById(addressId);
        User user = userRepository.findByEmail(email);

        if(!address.isPresent()){
            return new ResponseEntity<>("No address found with the given id;", HttpStatus.NOT_FOUND);
        }
        Address savedAddress = address.get();
        if(!savedAddress.getUser().getEmail().equals(email)){
            return new ResponseEntity<>("Invalid Operation", HttpStatus.CONFLICT);
        }

        if(addressDto.getAddressLine() != null)
            savedAddress.setAddressLine(addressDto.getAddressLine());

        if(addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());

        if(addressDto.getState() != null)
            savedAddress.setState(addressDto.getState());

        if(addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());

        if(addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());

        if(addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());

        return new ResponseEntity<>("Address Updated", HttpStatus.OK);
    }
    public String updateProfile(CustomerDto customer){
        String username=currentUserService.getUser();
        Customer customer1=customerRepository.findByUsername(username);
        if (customer.getFirstName()!=null)
            customer1.setFirstName(customer.getFirstName());
        if (customer.getMiddleName()!=null)
            customer1.setMiddleName(customer.getMiddleName());
        if (customer.getLastName()!=null)
            customer1.setLastName(customer.getLastName());
        if (customer.getContact()!=null)
        {
            if (customer.getContact().toString().matches("(\\+91|0)[0-9]{10}"))
            {
                customer1.setContact(customer.getContact());
            }
            else
            {
                return "Password mismatch";
            }
        }
        customerRepository.save(customer1);
        return "success";
    }
}


