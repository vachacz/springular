package com.github.springular.server.component.employee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.component.employee.EmployeeDO;
import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.SalaryDO;
import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;
import com.github.springular.server.component.employee.entity.EmployeeBE;
import com.github.springular.server.component.employee.entity.SalaryBE;
import com.github.springular.server.component.employee.repository.EmployeeRepository;
import com.github.springular.server.component.employee.repository.SalaryRepository;
import com.github.springular.server.exception.BusinessException;
import com.github.springular.server.exception.BusinessException.Builder;

public class EmployeeBCI implements IEmployeeBCI {

  @Autowired 
  SalaryRepository salaryRepository;
  
	@Autowired 
	EmployeeRepository employeeRepository;
	
	// TODO: find a way to convert between BE-DO
	
	@Override
	public List<EmployeeDO> getEmployees() {
	  List<EmployeeDO> result = new ArrayList<EmployeeDO>();;
	  List<EmployeeBE> all = employeeRepository.findAll();
	  for (EmployeeBE employeeBE : all) {
	    result.add(new EmployeeDO(employeeBE));
    }
		return result;
	}
	
  @Override
  public List<SalaryDO> getSalaries(SalaryQueryCriteriaDO criteria) {
    List<SalaryDO> result = new ArrayList<SalaryDO>();;
    List<SalaryBE> all = salaryRepository.filterByCriteria(criteria);
    for (SalaryBE employeeBE : all) {
      result.add(new SalaryDO(employeeBE));
    }
    return result;
  }

	@Override
	public void updateEmployee(@Valid EmployeeDO employee) {
	  EmployeeBE employeeBE = new EmployeeBE(employee);
	  
		Builder errorBuilder = BusinessException.build();

		if (! Character.isUpperCase(employeeBE.getFirstName().charAt(0))) {
			errorBuilder.addMessage("First name must be capitalized");
		}

		if (! Character.isUpperCase(employeeBE.getLastName().charAt(0))) {
			errorBuilder.addMessage("Last name must be capitalized");
		}

		if (errorBuilder.hasMessages()) {
			throw errorBuilder.exception();
		}
		
	  employeeRepository.save(employeeBE);
	}

	@Override
	public void deleteEmployee(String employeeId) {
	  employeeRepository.delete(Integer.valueOf(employeeId));
	}
	
}
