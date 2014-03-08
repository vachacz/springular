package com.github.springular.server.common.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.common.conversion.ConversionService;
import com.github.springular.server.common.conversion.ConversionService.ListConversionBuilder;
import com.github.springular.server.common.conversion.ConversionService.ObjectConversionBuilder;

public class BaseBCI {

  @Autowired 
  private ConversionService conversionService;
  
  protected <T> ObjectConversionBuilder<T> convert(T object) {
    return conversionService.convert(object);
  }

  protected <T> ListConversionBuilder<T> convert(List<T> object) {
    return conversionService.convert(object);
  }
  
}
