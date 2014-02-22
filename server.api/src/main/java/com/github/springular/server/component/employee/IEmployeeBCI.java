package com.github.springular.server.component.employee;

import java.util.List;

public interface IEmployeeBCI {

  public List<SalaryDO> getSalaries();
	public List<EmployeeDO> getEmployees();
	
	public void updateEmployee(EmployeeDO employee);
  public void deleteEmployee(String employeeId);

	
}
