package com.github.springular.server.component.employee;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class EmployeeDO {

	private Integer id;
	
	@NotEmpty(message = "{User.NotEmpty.login}")
	@Length(min = 8, max = 12, message = "{User.Length.login}")
	private String login;
	
	@NotEmpty(message = "{User.NotEmpty.firstName}")
	private String firstName;
	
	@NotEmpty(message = "{User.NotEmpty.lastName}")
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
	
}
