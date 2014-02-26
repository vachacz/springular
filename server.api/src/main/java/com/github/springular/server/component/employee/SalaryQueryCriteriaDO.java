package com.github.springular.server.component.employee;

public class SalaryQueryCriteriaDO {
  
  private String amount;
  private String employeeFirstName;
  private String employeeLastName;
  private Integer month;
  private Integer year;
  
  private Integer itemsProPage;
  private Integer pageNumber;
  private String orderType;
  
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
  
  public boolean matchesFirstName(String firstNameToExamine) {
    if (employeeFirstName.trim().length() == 0) {
      return true;
    }
    return firstNameToExamine.startsWith(employeeFirstName);
  }
  
  public boolean matchesLastName(String lastNameToExamine) {
    if (employeeLastName.trim().length() == 0) {
      return true;
    }
    return lastNameToExamine.startsWith(employeeLastName);
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
  public Integer getItemsProPage() {
    return itemsProPage;
  }
  public void setItemsProPage(Integer itemsProPage) {
    this.itemsProPage = itemsProPage;
  }
  public String getOrderType() {
    return orderType;
  }
  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }
  public Integer getPageNumber() {
    return pageNumber;
  }
  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }
  
}