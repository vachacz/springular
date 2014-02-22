package com.github.springular.server.component.employee.impl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.github.springular.server.component.employee.Nationality;
import com.github.springular.server.component.employee.EmployeeDO;

public class EmployeeDataStore {

	private Random random;
	private Map<String, EmployeeDO> employeeDB;
	
	@PostConstruct
	public void postConstruct() {
		random = new Random();
		employeeDB = new HashMap<String, EmployeeDO>();
		
		createEmployee("stoch", "Kamil","Stoch", Nationality.POLAND);
		createEmployee("zyla", "Piotr","Zyla",Nationality.POLAND);
		createEmployee("ziober", "Jan","Ziobro",Nationality.POLAND);
		createEmployee("hula", "Stefan","Hula",Nationality.POLAND);
		createEmployee("klimek", "Klemens","Muranka",Nationality.POLAND);
		createEmployee("kuba", "Dawid","Kubacki",Nationality.POLAND);
		createEmployee("biegun", "Krzysztof","Biegun",Nationality.POLAND);
		createEmployee("kot", "Maciej","Kot",Nationality.POLAND);
		createEmployee("destroy", "Aleksander","Zniszczol",Nationality.POLAND);
		createEmployee("bardal", "Anders","Bardal",Nationality.NORWAY);
		createEmployee("hilde", "Tom","Hilde",Nationality.NORWAY);
		createEmployee("velta", "Rune","Velta",Nationality.NORWAY);
		createEmployee("stjernen", "Anders","Stjernen",Nationality.NORWAY);
		createEmployee("friday", "Richard","Freitag",Nationality.GERMANY);
		createEmployee("hannawald", "Sven","Hannawald",Nationality.GERMANY);
		createEmployee("freund", "Severin","Freund",Nationality.GERMANY);
		createEmployee("jacobsen", "Anders","Jacobsen",Nationality.NORWAY);
		createEmployee("fanny", "Anders","Fannemel",Nationality.NORWAY);
		createEmployee("neumayer", "Michael","Neumeyer",Nationality.GERMANY);
		createEmployee("schmitt", "Martin","Schmitt",Nationality.GERMANY);
		createEmployee("wank", "Andreas","Wank",Nationality.GERMANY);
		createEmployee("wellinger", "Andreas","Wellinger",Nationality.GERMANY);
	}
	
	public List<EmployeeDO> getEmployees() {
		return new ArrayList<EmployeeDO>(employeeDB.values());
	}

	public EmployeeDO findEmployee(String id) {
		return employeeDB.get(id);
	}

  public void deleteEmployee(String id) {
    employeeDB.remove(id);
  }
	
	private void createEmployee(String login, String firstName, String lastName, Nationality nationality) {
		EmployeeDO employee = new EmployeeDO();
		employee.setId(Math.abs(random.nextInt()) + "");
		employee.setLogin(login);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setNationality(nationality.code());
		
		employeeDB.put(employee.getId(), employee);
	}

}
