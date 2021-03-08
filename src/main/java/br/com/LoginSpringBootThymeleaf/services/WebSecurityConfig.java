//package br.com.LoginSpringBootThymeleaf.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private UsuarioService usuarioRepositoryImpl;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
// 
// 
//		http.authorizeRequests()
//			.antMatchers("/usuario/novoCadastro").access("hasRole('ADMIN') or hasRole('ROLE_CADASTROUSUARIO')")
//			.antMatchers("/usuario/consultar").access("hasRole('ADMIN') or hasRole('CONSULTAUSUARIO')")
//			.antMatchers("/home").authenticated()
//			.anyRequest().authenticated()			
//			.and()			
//				.formLogin()
//				.loginPage("/").defaultSuccessUrl("/home",true)
//				.permitAll()
//			.and()
//				.logout()
//				.logoutSuccessUrl("/")
//				.logoutUrl("/logout") 
//				.permitAll();
//		http.exceptionHandling().accessDeniedPage("/acessoNegado");
//		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
//	}
//	
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(usuarioRepositoryImpl).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//}
