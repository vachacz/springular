package com.github.springular.server.component.employee;

public class SalaryQueryCriteriaDO {
  
  private String amount;
  private String employeeFirstName;
  private String employeeLastName;
  private Integer month;
  private Integer year;
  
  public String getAmount() {
    return amount;
  }
  public void setAmount(String amount) {
    this.amount = amount;
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
  public Integer getMonth() {
    return month;
  }
  public void setMonth(Integer month) {
    this.month = month;
  }
  public Integer getYear() {
    return year;
  }
  public void setYear(Integer year) {
    this.year = year;
  }
  
  public boolean matchesFirstName(String pattern) {
    if (employeeFirstName.trim().length() == 0) {
      return true;
    }
    return employeeFirstName.equals(pattern);
  }
  
  public boolean matchesLastName(String pattern) {
    if (employeeLastName.trim().length() == 0) {
      return true;
    }
    return employeeLastName.equals(pattern);
  }
  
  public boolean matchesMonth(Integer pattern) {
    if (month == null) {
      return true;
    }
    return month.intValue() == pattern.intValue();
  }
  
  public boolean matchesYear(Integer pattern) {
    if (year == null) {
      return true;
    }
    return year.intValue() == pattern.intValue();
  }
  
}