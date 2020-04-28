package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.ForgetAndResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ForgotController {

    @Autowired
    ForgetAndResetPasswordService forgetAndResetPasswordService;

    @PostMapping(path="/forgotPassword")
    public String forgotPassword(@RequestBody Users users){
       return   forgetAndResetPasswordService.forgot_password(users.getEmail());

    }

}
