package br.com.LoginSpringBootThymeleaf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioRepositoryImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/usuario/novoCadastro").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_CADASTROUSUARIO')")
			.antMatchers("/usuario").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONSULTAUSUARIO')")
			.antMatchers("/home").authenticated()
			.anyRequest().authenticated()			
			.and()			
				.formLogin()
				.loginPage("/").defaultSuccessUrl("/home", true)
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.logoutUrl("/logout") 
				.permitAll();
		
		http.exceptionHandling().accessDeniedPage("/acessoNegado");
		
	}
 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {				
		auth.userDetailsService(usuarioRepositoryImpl).passwordEncoder(new BCryptPasswordEncoder());
    }
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}