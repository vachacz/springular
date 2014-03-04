package com.github.springular.server.component.employee.repository;

import java.util.List;

import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;
import com.github.springular.server.component.employee.entity.SalaryBE;

public interface SalaryRepositoryCustom {
 
  public List<SalaryBE> filterByCriteria(SalaryQueryCriteriaDO criteria);
  
}
