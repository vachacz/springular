package com.github.springular.server.component.emploee.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.EmployeeDO;
import com.github.springular.server.component.employee.SalaryDO;
import com.github.springular.server.component.employee.impl.dao.EmployeeDataStore;
import com.github.springular.server.exception.BusinessException;
import com.github.springular.server.exception.BusinessException.Builder;

public class EmployeeBCI implements IEmployeeBCI {

	@Autowired
	EmployeeDataStore employeeDataStore;

	@Override
	public List<EmployeeDO> getEmployees() {
		return employeeDataStore.getEmployees();
	}

	@Override
	public void updateEmployee(@Valid EmployeeDO employee) {
		EmployeeDO persistentEmployee = employeeDataStore.findEmployee(employee.getId());

		Builder errorBuilder = BusinessException.build();

		if (isUpper(employee.getFirstName())) {
			errorBuilder.addMessage("Uppercase first name is not allowed");
		}

		if (isUpper(employee.getLastName())) {
			errorBuilder.addMessage("Uppercase last name is not allowed");
		}

		if (errorBuilder.hasMessages()) {
			throw errorBuilder.exception();
		}

		persistentEmployee.setLogin(employee.getLogin());
		persistentEmployee.setFirstName(employee.getFirstName());
		persistentEmployee.setLastName(employee.getLastName());
	}

	@Override
	public void deleteEmployee(String employeeId) {
	  employeeDataStore.deleteEmployee(employeeId);
	}
	
	public static boolean isUpper(String string) {
		for (char c : string.toCharArray()) {
			if (!Character.isUpperCase(c))
				return false;
		}
		return true;
	}

  @Override
  public List<SalaryDO> getSalaries() {
    return employeeDataStore.getSalaries();
  }


}
