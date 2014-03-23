package com.github.springular.server.component.employee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EmployeeBE {

  @Id 
  @GeneratedValue
  private Integer id;
  
  @OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
  private List<SalaryBE> salaries;
  
  private String login;
  private String firstName;
  private String lastName;
  private String nationality;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public List<SalaryBE> getSalaries() {
    return salaries;
  }

  public void setSalaries(List<SalaryBE> salaries) {
    this.salaries = salaries;
  }
  
}
