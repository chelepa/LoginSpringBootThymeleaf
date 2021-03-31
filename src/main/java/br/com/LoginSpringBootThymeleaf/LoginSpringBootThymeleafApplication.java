package br.com.LoginSpringBootThymeleaf;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@SpringBootApplication
public class LoginSpringBootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginSpringBootThymeleafApplication.class, args);
	}
	
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
    
    @Bean
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }

}
