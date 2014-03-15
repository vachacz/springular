package com.github.springular.server.component.employee.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.common.component.BaseBCI;
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

public class EmployeeBCI extends BaseBCI implements IEmployeeBCI {

  @Autowired 
  SalaryRepository salaryRepository;
  
  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public List<EmployeeDO> getEmployees() {
    List<EmployeeBE> employees = employeeRepository.findAll();
    return convert(employees).toType(EmployeeDO.class);
  }
	
  @Override
  public List<SalaryDO> getSalaries(SalaryQueryCriteriaDO criteria) {
    List<SalaryBE> salaries = salaryRepository.filterByCriteria(criteria);
    // List<SalaryBE> salaries = salaryRepository.findSalariesByYearAndMonth(2013, 2);
    // List<SalaryBE> salaries = salaryRepository.findSalariesByYear(2013);
    return convert(salaries).toType(SalaryDO.class);
  }
  
	@Override
	public void createOrUpdateEmployee(@Valid EmployeeDO employee) {
	  EmployeeBE employeeBE = convert(employee).toType(EmployeeBE.class);

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
	public void deleteEmployee(Integer employeeId) {
	  employeeRepository.delete(employeeId);
	}
	
}
