package br.com.LoginSpringBootThymeleaf.services.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.LoginSpringBootThymeleaf.services.Usuarios.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ROLE_ADMIN = "hasRole('ROLE_ADMIN')";

	@Autowired
	private UsuarioService usuarioRepositoryImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/usuario").access(ROLE_ADMIN)
			.antMatchers("/usuario/cadastro").access(ROLE_ADMIN)
			.antMatchers("/usuario/editar").access(ROLE_ADMIN)
			.antMatchers("/grupo").access(ROLE_ADMIN)
			.antMatchers("/grupoario/cadastro").access(ROLE_ADMIN)
			.antMatchers("/grupo/editar").access(ROLE_ADMIN)
			.antMatchers("/permissoes").access(ROLE_ADMIN)
			.antMatchers("/permissoes/cadastro").access(ROLE_ADMIN)
			.antMatchers("/permissoes/editar").access(ROLE_ADMIN)
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