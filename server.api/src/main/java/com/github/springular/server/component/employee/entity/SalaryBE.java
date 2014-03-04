package com.github.springular.server.component.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SalaryBE {

  @Id 
  @GeneratedValue
  private Integer id;

  @ManyToOne
  private EmployeeBE employee;
  
  private Integer year;
  private Integer month;
  private Integer amount;
  
  public SalaryBE() {
  }
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public EmployeeBE getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeBE employee) {
    this.employee = employee;
  }
  
}
