package com.github.springular.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.SalaryDO;
import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;

@Controller
public class SalaryController {

  @Autowired
  private IEmployeeBCI employeeBCI;
  
  @ResponseBody
  @RequestMapping(value = "/secured/salary", method = RequestMethod.POST)
  public List<SalaryDO> getSalaries(@RequestBody SalaryQueryCriteriaDO criteria) {
    return employeeBCI.getSalaries(criteria);
  }
	
}
