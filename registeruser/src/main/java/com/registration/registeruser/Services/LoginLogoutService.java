package com.registration.registeruser.Services;

import com.registration.registeruser.MailService;
import com.registration.registeruser.SendMail;
import com.registration.registeruser.entity.User;
import com.registration.registeruser.repository.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Service
public class LoginLogoutService {
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private MessageSource messageSource;
    // @Autowired
    //  private TokensService tokensService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Map login(WebRequest webRequest, String username, String password, String clientId, String clientSecret) throws Exception {

        Locale locale = webRequest.getLocale();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        if (password.length() == 0 || clientId.length() == 0 || clientSecret.length() == 0 || username.length() == 0) {
            System.out.println("Null entries not allowed");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
//            int temp=user.getFalseAttemptCount();
//            user.setFalseAttemptCount(++temp);
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+temp);
//            if(temp==3)
//            {
//                user.setAccountNonLocked(true);
//                userRepository.save(user);
//            }
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+temp);
            System.out.println("Invalid password");
        }
        if (!user.isActive()) {
            System.out.println("User is not active");
        } else if (!user.isAccountNonLocked()) {
            String message = messageSource.getMessage("message.userAccountLocked", null, locale);
            String subject = "Account Locked";
            sendMail.sendEmail(user.getUsername(), subject, message);
            throw new Exception("Account Locked");
        } else {

            return createToken(clientId, clientSecret, username, password);
        }

        return login(webRequest, username, password, clientId, clientSecret);
    }
    public Map createToken(String clientId, String clientSecret, String email, String password) {


        RestTemplate restTemplate = new RestTemplate();

        // According OAuth documentation we need to send the client id and secret key in the header for authentication
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "http://localhost:8080/oauth/token";
        access_token_url += "?grant_type=password&client_id=" + clientId + "&client_secret=" + clientSecret + "&password=" + password + "&username=" + email;

        ResponseEntity<Map> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, Map.class);

        System.out.println("Access Token Response ---------" + response.getBody());
        return response.getBody();

    }
}
