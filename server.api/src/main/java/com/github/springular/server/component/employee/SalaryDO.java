package com.github.springular.server.component.employee;

import com.github.springular.server.component.employee.entity.SalaryBE;

public class SalaryDO {
  
  private Integer id;
  private Integer employeeId;
  
  private String employeeFirstName;
  private String employeeLastName;
  private Integer year;
  private Integer month;
  private Integer amount;
  
  public SalaryDO() {
  }
  
  public SalaryDO(SalaryBE salaryBE) {
     id = salaryBE.getId();               
     employeeId = salaryBE.getEmployee().getId();       
     employeeFirstName = salaryBE.getEmployee().getFirstName();
     employeeLastName= salaryBE.getEmployee().getLastName(); 
     year = salaryBE.getYear();            
     month = salaryBE.getMonth();           
     amount = salaryBE.getAmount();          
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getEmployeeId() {
    return employeeId;
  }
  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }
  public String getEmployeeFirstName() {
    return employeeFirstName;
  }
  public void setEmployeeFirstName(String employeeFirstName) {
    this.employeeFirstName = employeeFirstName;
  }
  public String getEmployeeLastName() {
    return employeeLastName;
  }
  public void setEmployeeLastName(String employeeLastName) {
    this.employeeLastName = employeeLastName;
  }
  public Integer getYear() {
    return year;
  }
  public void setYear(Integer year) {
    this.year = year;
  }
  public Integer getMonth() {
    return month;
  }
  public void setMonth(Integer month) {
    this.month = month;
  }
  public Integer getAmount() {
    return amount;
  }
  public void setAmount(Integer amount) {
    this.amount = amount;
  }
  	
}