package com.github.springular.server.component.employee.entity;

import com.github.springular.server.common.conversion.Converter;
import com.github.springular.server.component.employee.SalaryDO;

public class SalaryConverters {

  public static class BEToDOConverter implements Converter<SalaryBE, SalaryDO> {
    @Override
    public SalaryDO convert(SalaryBE salary) {
      SalaryDO salaryDO = new SalaryDO();
      salaryDO.setId(salary.getId());               
      salaryDO.setEmployeeId(salary.getEmployee().getId());       
      salaryDO.setEmployeeFirstName(salary.getEmployee().getFirstName());
      salaryDO.setEmployeeLastName(salary.getEmployee().getLastName()); 
      salaryDO.setYear(salary.getYear());            
      salaryDO.setMonth(salary.getMonth());           
      salaryDO.setAmount(salary.getAmount());   
      return salaryDO;
    }
  }
  
}
