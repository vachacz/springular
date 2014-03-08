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

import com.github.springular.server.common.conversion.ConversionService;
import com.github.springular.server.common.conversion.Converter;
import com.github.springular.server.configuration.aspect.ValidationAspect;
import com.github.springular.server.configuration.db.DummyDataCreator;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { 
    "com.github.springular.server.component" 
}, includeFilters = { 
    @Filter( type = FilterType.REGEX, pattern = { ".*BCI" } ),
    @Filter( type = FilterType.ASSIGNABLE_TYPE, value = Converter.class ) 
})
public class BackendConfiguration {

  @Bean
  public ValidationAspect validationAspect() {
    return new ValidationAspect();
  }

  @Bean
  public ConversionService conversionService() {
     return new ConversionService();
  }
  
  @Bean
  public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
    return new CommonAnnotationBeanPostProcessor();
  }
  
  @Bean
  public DummyDataCreator dummyDataCreator() {
    return new DummyDataCreator();
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
