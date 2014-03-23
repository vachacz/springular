package com.github.springular.server.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.github.springular.server.rest.common.GlobalExceptionHandler;
import com.github.springular.server.rest.controller.ControllerPackegeMarker;

@Configuration
@ComponentScan(basePackageClasses = { ControllerPackegeMarker.class })
public class JsonEndpointConfiguration extends WebMvcConfigurationSupport {

  @Autowired
  private MappingJackson2HttpMessageConverter MappingJackson2HttpMessageConverter;
  
  @Bean
  public GlobalExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler();
  }
  
  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter();
  }
  
  @Bean
  public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
    RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
    adapter.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(MappingJackson2HttpMessageConverter));
    return adapter;
  }
  
  @Bean
  public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
    ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver(); 
    resolver.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(MappingJackson2HttpMessageConverter));
    return resolver;
  }
  
}
