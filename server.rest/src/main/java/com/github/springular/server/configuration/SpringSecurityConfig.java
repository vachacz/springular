package com.github.springular.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.github.springular.server.configuration.auth.UserAuthenticationProvider;
import com.github.springular.server.configuration.rest.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
  @Bean
  public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }
  
  @Bean
  public UserAuthenticationProvider userAuthenticationProvider() {
    return new UserAuthenticationProvider();
  }
  
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthenticationProvider());
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
		    .authenticationEntryPoint(restAuthenticationEntryPoint())
		  .and()
		    .authorizeRequests()
		      .antMatchers("/secured/*").hasAuthority("USER")
		      .antMatchers(HttpMethod.POST, "/**").hasAuthority("USER")
		      .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("USER")
		      .anyRequest().permitAll();
	}
		
}