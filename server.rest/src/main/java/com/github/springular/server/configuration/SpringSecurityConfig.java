package com.github.springular.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
  @Autowired private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
  
  @Bean
  public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }
  
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("user").authorities("USER")
			.and()
			  .withUser("admin").password("admin").authorities("USER");
	  }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		  .csrf()
		    .disable()
		  .logout()
		    .logoutUrl("/signout").permitAll()
		  .and()
		  .httpBasic()
		    .authenticationEntryPoint(restAuthenticationEntryPoint)
		  .and()
		    .authorizeRequests()
		      .antMatchers("/secured/*").hasAuthority("USER")
		      .anyRequest().permitAll();
	}
		
}