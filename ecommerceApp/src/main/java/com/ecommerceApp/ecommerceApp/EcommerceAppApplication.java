package com.ecommerceApp.ecommerceApp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableAsync

public class EcommerceAppApplication {

	@Bean
	ModelMapper getModelMapperBean()
	{
		return  new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

}
