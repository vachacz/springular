package com.github.springular.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.github.springular.server.component.user.impl.UserBCI;
import com.github.springular.server.configuration.aspect.ValidationAspect;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { 
    "com.github.springular.server.component" 
}, includeFilters = { 
    @Filter( type = FilterType.REGEX, pattern = { ".*BCI", ".*Store" } ) 
})
public class BackendConfiguration {

  @Bean
  public UserBCI userBCI() {
    return new UserBCI();
  }
  
  @Bean
  public ValidationAspect validationAspect() {
    return new ValidationAspect();
  }
  
  @Bean
  public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
    return new CommonAnnotationBeanPostProcessor();
  }
  
  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages/validation");
    
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    factoryBean.setValidationMessageSource(messageSource);
    return factoryBean;
  }
    
}
