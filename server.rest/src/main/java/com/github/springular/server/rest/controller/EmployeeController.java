package com.github.springular.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.EmployeeDO;

@Controller
public class EmployeeController {

	@Autowired IEmployeeBCI employeeBCI;
	
	@ResponseBody
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<EmployeeDO> getEmployees() {
		return employeeBCI.getEmployees();
	}
	
  @ResponseBody
  @RequestMapping(value = "/employee/{id}", method = RequestMethod.POST)
  public void updateEmployee(@RequestBody EmployeeDO employee) {
    employeeBCI.createOrUpdateEmployee(employee);
  }
    
  @ResponseBody
  @RequestMapping(value = "/employee", method = RequestMethod.POST)
  public void createEmployee(@RequestBody EmployeeDO employee) {
    employeeBCI.createOrUpdateEmployee(employee);
  }
  
  @ResponseBody
  @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
  public void deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
    employeeBCI.deleteEmployee(employeeId);
  }
    
}

