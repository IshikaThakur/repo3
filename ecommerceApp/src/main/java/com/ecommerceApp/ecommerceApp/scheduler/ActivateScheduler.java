package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ActivateScheduler {
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ActivateScheduler.class);
    private static final DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Scheduled(fixedRate = 8640000)
    public void unlockAccount() {
        List<Users> list = userRepository.findByisActive(false);
        for (Users user : list) {
            user.setActive(true);
            userRepository.save(user);
        }

    }
}
