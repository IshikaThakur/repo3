package com.registration.registeruser.Controller;


import com.registration.registeruser.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    TokenStore tokenStore;

            @PostMapping("/forgotpassword")
    String forgotPassword(@RequestParam("email") String email){
        userService.forgotPassword(email);
        return "Generating a token and sending to mail";
    }

    @GetMapping("/resetpassword")  //working successfully
    String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password){
        userService.setPassword(token, password);
        return "password reset successful";
    }
    @GetMapping("/doLogout")                   //not working
    public String logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return "Logged out successfully";
    }

}
