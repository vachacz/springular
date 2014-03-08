package com.github.springular.server.common.conversion;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public class ConversionService {

  @Autowired
  List<Converter<?, ?>> converters;
  
  Map<ConversionDescriptor, Converter<?, ?>> converterMap = new HashMap<ConversionDescriptor, Converter<?, ?>>();
  
  @PostConstruct
  public void createinternalModel() {
    for (Converter<?, ?> converter : converters) {
      Type[] interfaces = converter.getClass().getGenericInterfaces();
      for (Type type : interfaces) {
        ParameterizedType pType = (ParameterizedType) type;
        
        Type[] typeArguments = pType.getActualTypeArguments();
        Class<?> from = (Class<?>) typeArguments[0];
        Class<?> to   = (Class<?>) typeArguments[1];
        
        converterMap.put(new ConversionDescriptor(from, to), converter);
      }
    }
  }
  
  public class ListConversionBuilder<T> {
   
    private List<T> objects;
    
    public ListConversionBuilder(List<T> objects) {
      this.objects = objects;
    }
    
    public <V> List<V> toType(Class<V> clazz) {
      List<V> result = new ArrayList<V>();
      for (T object : objects) {
        result.add(convert(object).toType(clazz));
      }
      return result;
    }
  }
  
  public class ObjectConversionBuilder<T> {
    
    private T object;
    
    public ObjectConversionBuilder(T object) {
      this.object = object;
    }
    
    @SuppressWarnings("unchecked")
    public <V> V toType(Class<V> clazz) {
      ConversionDescriptor conversionDescriptor = new ConversionDescriptor(object.getClass(), clazz);
      if (converterMap.containsKey(conversionDescriptor)) {
        return ((Converter<T, V>) converterMap.get(conversionDescriptor)).convert(object);
      } else {
        throw new RuntimeException("converter not found");
      }
    }
  }
  
  public <T> ListConversionBuilder<T> convert(List<T> object) {
    return new ListConversionBuilder<T>(object);
  }
  
  public <T> ObjectConversionBuilder<T> convert(T object) {
    return new ObjectConversionBuilder<T>(object);
  }
  
}
