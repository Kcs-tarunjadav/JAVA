package com.BugTracker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	// authencation
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);

	}

	
	//authrization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		
		
		http.
		csrf().disable();
		http.authorizeRequests().antMatchers("/login").permitAll()
		                                          .and().authorizeRequests().antMatchers("/**").authenticated().and().httpBasic()
		                                         .and()
		                                         .formLogin()
		                              		     .loginPage("/login").permitAll()
		                              		     .and()
		                              		     .logout().invalidateHttpSession(true)
		                              		     .clearAuthentication(true)
		                              		     .logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
		                              		     .logoutSuccessUrl("/logout-success").permitAll();
	
		
		
	}
	

}
