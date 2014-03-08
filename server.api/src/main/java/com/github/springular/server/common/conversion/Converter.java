package com.github.springular.server.common.conversion;

public interface Converter<A, B> {
  
  public B convert(A object);
  
}