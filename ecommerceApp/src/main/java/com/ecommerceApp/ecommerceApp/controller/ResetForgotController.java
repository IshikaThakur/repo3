package com.ecommerceApp.ecommerceApp.controller;

import com.ecommerceApp.ecommerceApp.dtos.PasswordDto;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.services.ForgetAndResetPasswordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ResetForgotController {

    @Autowired
    ForgetAndResetPasswordService forgetAndResetPasswordService;
    @ApiOperation("Forgot Password")
    @PostMapping(path="/forgotPassword")
    public String forgotPassword(@RequestBody Users users){
       return   forgetAndResetPasswordService.forgot_password(users.getEmail());

    }
    @ApiOperation("To reset the forgo password")
    @PostMapping(path = "/resetPassword/{token}")
    public String resetPassword(@Valid @RequestBody PasswordDto passwordDto,@PathVariable("token")String token )
{
    return forgetAndResetPasswordService.resetPassword(passwordDto, token);
}
}
