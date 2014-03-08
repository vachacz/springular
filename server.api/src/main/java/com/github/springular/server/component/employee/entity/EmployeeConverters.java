package com.github.springular.server.component.employee.entity;

import com.github.springular.server.common.conversion.Converter;
import com.github.springular.server.component.employee.EmployeeDO;

public class EmployeeConverters {

  public static class DoToBEConverter implements Converter<EmployeeDO, EmployeeBE> {
    @Override
    public EmployeeBE convert(EmployeeDO employee) {
      EmployeeBE employeeBE = new EmployeeBE();
      if (employee.getId() != null) {
        employeeBE.setId(employee.getId());
      }
      employeeBE.setLogin(employee.getLogin());
      employeeBE.setFirstName(employee.getFirstName());
      employeeBE.setLastName(employee.getLastName());
      employeeBE.setNationality(employee.getNationality());
      return employeeBE;
    }
  }
  
  public static class BEToDOConverter implements Converter<EmployeeBE, EmployeeDO> {
    @Override
    public EmployeeDO convert(EmployeeBE employee) {
      EmployeeDO employeeDO = new EmployeeDO();
      employeeDO.setId(employee.getId());
      employeeDO.setLogin(employee.getLogin());
      employeeDO.setFirstName(employee.getFirstName());
      employeeDO.setLastName(employee.getLastName());
      employeeDO.setNationality(employee.getNationality());
      return employeeDO;
    }
  }
  
}
