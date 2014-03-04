package com.github.springular.server.configuration.db;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.component.employee.Nationality;
import com.github.springular.server.component.employee.entity.EmployeeBE;
import com.github.springular.server.component.employee.entity.SalaryBE;
import com.github.springular.server.component.employee.repository.EmployeeRepository;
import com.github.springular.server.component.employee.repository.SalaryRepository;

public class DummyDataCreator {

  @Autowired
  EmployeeRepository employeeRepository;
  
  @Autowired
  SalaryRepository salaryRepository;

  @PostConstruct
  public void postConstruct() {
    salaryRepository.deleteAll();;
    employeeRepository.deleteAll();;
    
    List<EmployeeBE> employees = Arrays.asList(
      employee("stoch", "Kamil","Stoch", Nationality.POLAND),
      employee("zyla", "Piotr","Zyla",Nationality.POLAND),
      employee("ziober", "Jan","Ziobro",Nationality.POLAND),
      employee("hula", "Stefan","Hula",Nationality.POLAND),
      employee("klimek", "Klemens","Muranka",Nationality.POLAND),
      employee("kuba", "Dawid","Kubacki",Nationality.POLAND),
      employee("biegun", "Krzysztof","Biegun",Nationality.POLAND),
      employee("kot", "Maciej","Kot",Nationality.POLAND),
      employee("destroy", "Aleksander","Zniszczol",Nationality.POLAND),
      employee("bardal", "Anders","Bardal",Nationality.NORWAY),
      employee("hilde", "Tom","Hilde",Nationality.NORWAY),
      employee("velta", "Rune","Velta",Nationality.NORWAY),
      employee("stjernen", "Anders","Stjernen",Nationality.NORWAY),
      employee("friday", "Richard","Freitag",Nationality.GERMANY),
      employee("hannawald", "Sven","Hannawald",Nationality.GERMANY),
      employee("freund", "Severin","Freund",Nationality.GERMANY),
      employee("jacobsen", "Anders","Jacobsen",Nationality.NORWAY),
      employee("fanny", "Anders","Fannemel",Nationality.NORWAY),
      employee("neumayer", "Michael","Neumeyer",Nationality.GERMANY),
      employee("schmitt", "Martin","Schmitt",Nationality.GERMANY),
      employee("wank", "Andreas","Wank",Nationality.GERMANY),
      employee("wellinger", "Andreas","Wellinger",Nationality.GERMANY)
    );
    
    Random random = new Random();
    for (EmployeeBE employee : employees) {
      employeeRepository.save(employee);
      
      for (int year = 2013; year <= 2014; year++) {
        for (int month = 1; month <= 4; month++) {
          salaryRepository.save(salary(employee, year, month, 1000 + random.nextInt(1000)));
        }
      }
    }
  }
  
  private EmployeeBE employee(String login, String firstName, String lastName, Nationality nationality) {
    EmployeeBE employee = new EmployeeBE();
    employee.setLogin(login);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setNationality(nationality.code());
    return employee;
  }
  
  private SalaryBE salary(EmployeeBE employee, int year, int month, int amount) {
    SalaryBE salary = new SalaryBE();
    salary.setEmployee(employee);
    salary.setMonth(month);
    salary.setYear(year);
    salary.setAmount(amount);
    return salary;
  }
  
}
