package com.lihao.crm.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// @formatter:off
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.headers()
				.frameOptions().sameOrigin()
				.and()
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/login-error").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/jquery-easyui-1.5.4/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/salesman/**").hasRole("SALESMAN")
				.antMatchers("/technicist/**").hasRole("TECHNICIST")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/index")
				.failureUrl("/login?error=true")	
				.and()
			.csrf()
				.disable()
		;	
		
    }
	// @formatter:on

}
