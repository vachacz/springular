package com.github.springular.server.component.employee;

public class SalaryDO {
  
  private String id;
  private String employeeId;
  private String employeeFirstName;
  private String employeeLastName;
  private Integer year;
  private Integer month;
  private Integer amount;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getEmployeeId() {
    return employeeId;
  }
  public void setEmployeeId(String employeeId) {
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