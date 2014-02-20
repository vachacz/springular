package com.github.springular.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;

import com.github.springular.server.rest.controller.ControllerPackegeMarker;

@Configuration
@ComponentScan(basePackageClasses = { ControllerPackegeMarker.class })
public class JsonEndpointConfig {

  @Autowired
  private MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter;
  
  @Bean
  public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter() {
    return new MappingJacksonHttpMessageConverter();
  }
  
  @Bean
  public AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter() {
    AnnotationMethodHandlerAdapter adapter = new AnnotationMethodHandlerAdapter();
    adapter.setMessageConverters(new HttpMessageConverter<?> [] { mappingJacksonHttpMessageConverter });
    return adapter;
  }
  
  @Bean
  public AnnotationMethodHandlerExceptionResolver annotationMethodHandlerExceptionResolver() {
    AnnotationMethodHandlerExceptionResolver adapter = new AnnotationMethodHandlerExceptionResolver();
    adapter.setMessageConverters(new HttpMessageConverter<?> [] { mappingJacksonHttpMessageConverter });
    return adapter;
  }
  
  
  
}
