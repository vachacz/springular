package com.github.springular.server.component.emploee.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.EmployeeDO;
import com.github.springular.server.component.employee.SalaryDO;
import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;
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

	// TODO move to datastore ... and finally replace with db-implementation
  @Override
  public List<SalaryDO> getSalaries(SalaryQueryCriteriaDO criteria) {
    List<SalaryDO> result = new ArrayList<SalaryDO>();
    
    List<SalaryDO> salaries = employeeDataStore.getSalaries();
    for (SalaryDO salaryDO : salaries) {
      
      if (criteria.matchesFirstName(salaryDO.getEmployeeFirstName())
          && criteria.matchesLastName(salaryDO.getEmployeeLastName())
          && criteria.matchesYear(salaryDO.getYear())
          && criteria.matchesMonth(salaryDO.getMonth())
        ) {
        result.add(salaryDO);
      }
    }

    if (criteria.getOrderType() != null) {
      String[] split = criteria.getOrderType().split("\\.");
      boolean reverse = "desc".equals(split[1]);
          
      Comparator<SalaryDO> comparator = getComparator(split[0]);
      if (comparator != null) {
        Collections.sort(result, comparator);
      }
          
      if (reverse) {
        Collections.reverse(result);
      }
    }
    
    int itemsProPage = ( criteria.getItemsProPage() != null ? criteria.getItemsProPage() : 10 );
    int pageNumber = ( criteria.getPageNumber() != null ? criteria.getPageNumber() : 0);
    
    int startIndex = itemsProPage * pageNumber;
    int endIndex = startIndex + itemsProPage;
    if (endIndex > result.size()) {
      endIndex = result.size();
    }
    
    return result.subList(startIndex, endIndex);
  }

  private Comparator<SalaryDO> getComparator(String orderType) {
    switch (orderType) {
    case "firstName": return new FirstNameComparator();
    case "lastName": return new LastNameComparator();
    case "year": return new YearComparator();
    case "month": return new MonthComparator();
    case "amount": return new AmountComparator();
    }
    return null;
  }

  class FirstNameComparator implements Comparator<SalaryDO> {
    @Override
    public int compare(SalaryDO o1, SalaryDO o2) {
      return o1.getEmployeeFirstName().compareTo(o2.getEmployeeFirstName());
    }
  }
  
  class LastNameComparator implements Comparator<SalaryDO> {
    @Override
    public int compare(SalaryDO o1, SalaryDO o2) {
      return o1.getEmployeeLastName().compareTo(o2.getEmployeeLastName());
    }
  }
  
  class MonthComparator implements Comparator<SalaryDO> {
    @Override
    public int compare(SalaryDO o1, SalaryDO o2) {
      return o1.getMonth().compareTo(o2.getMonth());
    }
  }
  
  class YearComparator implements Comparator<SalaryDO> {
    @Override
    public int compare(SalaryDO o1, SalaryDO o2) {
      return o1.getYear().compareTo(o2.getYear());
    }
  }
  
  class AmountComparator implements Comparator<SalaryDO> {
    @Override
    public int compare(SalaryDO o1, SalaryDO o2) {
      return o1.getAmount().compareTo(o2.getAmount());
    }
  }
}
