package com.github.springular.server.component.employee;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.github.springular.server.component.employee.entity.EmployeeBE;

public class EmployeeDO {

	private String id;
	
	@NotEmpty(message = "{User.NotEmpty.login}")
	@Length(min = 8, max = 12, message = "{User.Length.login}")
	private String login;
	
	@NotEmpty(message = "{User.NotEmpty.firstName}")
	private String firstName;
	
	@NotEmpty(message = "{User.NotEmpty.lastName}")
	private String lastName;
	
	private String nationality;
	
	public EmployeeDO() {
  }
	
	public EmployeeDO(EmployeeBE employeeBE) {
	  id = employeeBE.getId().toString();
	  login = employeeBE.getLogin();
	  firstName = employeeBE.getFirstName();
	  lastName = employeeBE.getLastName();
	  nationality = employeeBE.getNationality();
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
