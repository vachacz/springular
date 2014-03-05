package com.github.springular.server.component.employee;

import com.google.common.base.Strings;

public class SalaryQueryCriteriaDO {
  
  private String employeeFirstName;
  private String employeeLastName;
  private Integer month;
  private Integer year;
  private Integer amount;
  
  private Integer itemsProPage;
  private String orderType;
  
  public Integer getAmount() {
    return amount;
  }
  public void setAmount(Integer amount) {
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
  public String getOrderType() {
    return orderType;
  }
  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }
  
  public boolean hasAmount() {
    return amount != null;
  }
  public boolean hasEmployeeLastName() {
    return ! Strings.isNullOrEmpty(employeeLastName);
  }
  public boolean hasEmployeeFirstName() {
    return ! Strings.isNullOrEmpty(employeeFirstName);
  }
  public Integer getItemsProPage() {
    return itemsProPage;
  }
  public void setItemsProPage(Integer itemsProPage) {
    this.itemsProPage = itemsProPage;
  }
  
  public boolean isOrderSpecified() {
    return this.orderType != null;
  }
  
}