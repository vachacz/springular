package com.github.springular.server.component.employee.impl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.github.springular.server.component.employee.Nationality;
import com.github.springular.server.component.employee.EmployeeDO;
import com.github.springular.server.component.employee.SalaryDO;

public class EmployeeDataStore {

	private Random random;
	private Map<String, EmployeeDO> employeeDB;
  private Map<String, SalaryDO> salaryDB;
	
	@PostConstruct
	public void postConstruct() {
		random = new Random();
		employeeDB = new HashMap<String, EmployeeDO>();
		salaryDB = new HashMap<String, SalaryDO>();
		
		employee("stoch", "Kamil","Stoch", Nationality.POLAND);
		employee("zyla", "Piotr","Zyla",Nationality.POLAND);
		employee("ziober", "Jan","Ziobro",Nationality.POLAND);
		employee("hula", "Stefan","Hula",Nationality.POLAND);
		employee("klimek", "Klemens","Muranka",Nationality.POLAND);
		employee("kuba", "Dawid","Kubacki",Nationality.POLAND);
		employee("biegun", "Krzysztof","Biegun",Nationality.POLAND);
		employee("kot", "Maciej","Kot",Nationality.POLAND);
		employee("destroy", "Aleksander","Zniszczol",Nationality.POLAND);
		employee("bardal", "Anders","Bardal",Nationality.NORWAY);
		employee("hilde", "Tom","Hilde",Nationality.NORWAY);
		employee("velta", "Rune","Velta",Nationality.NORWAY);
		employee("stjernen", "Anders","Stjernen",Nationality.NORWAY);
		employee("friday", "Richard","Freitag",Nationality.GERMANY);
		employee("hannawald", "Sven","Hannawald",Nationality.GERMANY);
		employee("freund", "Severin","Freund",Nationality.GERMANY);
		employee("jacobsen", "Anders","Jacobsen",Nationality.NORWAY);
		employee("fanny", "Anders","Fannemel",Nationality.NORWAY);
		employee("neumayer", "Michael","Neumeyer",Nationality.GERMANY);
		employee("schmitt", "Martin","Schmitt",Nationality.GERMANY);
		employee("wank", "Andreas","Wank",Nationality.GERMANY);
		employee("wellinger", "Andreas","Wellinger",Nationality.GERMANY);
		
		for (String employeeId : employeeDB.keySet()) {
		  EmployeeDO employeeDO = employeeDB.get(employeeId);
		  
		  for (int year = 2008; year <= 2014; year++) {
		    for (int month = 1; month <= 12; month++) {
		      salary(employeeDO, year, month, 1000 + random.nextInt(1000));
		    }
		  }
		}
	}
	
  public List<EmployeeDO> getEmployees() {
		return new ArrayList<EmployeeDO>(employeeDB.values());
	}

	public List<SalaryDO> getSalaries() {
	  return new ArrayList<SalaryDO>(salaryDB.values());
	}
	
	public EmployeeDO findEmployee(String id) {
		return employeeDB.get(id);
	}

  public void deleteEmployee(String id) {
    employeeDB.remove(id);
  }
  
  private void employee(String login, String firstName, String lastName, Nationality nationality) {
    EmployeeDO employee = new EmployeeDO();
    employee.setId(Math.abs(random.nextInt()) + "");
    employee.setLogin(login);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setNationality(nationality.code());
    
    employeeDB.put(employee.getId(), employee);
  }

  
  private void salary(EmployeeDO employeeDO, int year, int month, int amount) {
    SalaryDO salary = new SalaryDO();
    salary.setId(Math.abs(random.nextInt()) + "");
    salary.setEmployeeId(employeeDO.getId());
    salary.setEmployeeFirstName(employeeDO.getFirstName());
    salary.setEmployeeLastName(employeeDO.getLastName());
    salary.setMonth(month);
    salary.setYear(year);
    salary.setAmount(amount);
    
    salaryDB.put(salary.getId(), salary);
  }
  
}
