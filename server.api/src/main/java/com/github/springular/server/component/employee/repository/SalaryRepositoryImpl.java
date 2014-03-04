package com.github.springular.server.component.employee.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;
import com.github.springular.server.component.employee.entity.QEmployeeBE;
import com.github.springular.server.component.employee.entity.QSalaryBE;
import com.github.springular.server.component.employee.entity.SalaryBE;
import com.mysema.query.jpa.impl.JPAQuery;

public class SalaryRepositoryImpl implements SalaryRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;
  
  @Override
  public List<SalaryBE> filterByCriteria(SalaryQueryCriteriaDO criteria) {
    
    QEmployeeBE employee = QEmployeeBE.employeeBE;
    QSalaryBE salary = QSalaryBE.salaryBE;
    JPAQuery query = new JPAQuery(entityManager);
    query.from(salary).join(salary.employee, employee);
    
    if (criteria.getMonth() != null) {
      query.where(salary.month.eq(criteria.getMonth()));
    }
    if (criteria.getYear() != null) {
      query.where(salary.year.eq(criteria.getYear()));
    }
    if (criteria.hasAmount()) {
      query.where(salary.amount.eq(Integer.valueOf(criteria.getAmount())));
    }
    if (criteria.hasEmployeeFirstName()) {
      query.where(employee.firstName.containsIgnoreCase(criteria.getEmployeeFirstName()));
    }
    if (criteria.hasEmployeeLastName()) {
      query.where(employee.lastName.containsIgnoreCase(criteria.getEmployeeLastName()));
    }
    
    if (criteria.getOrderType() != null) {
      String order = criteria.getOrderType();
      
      if ("firstName".equals(order)) {
        query.orderBy(employee.firstName.asc());
      } else if ("lastName".equals(order)) {
        query.orderBy(employee.lastName.asc());
      } else if ("year".equals(order)) {
        query.orderBy(salary.year.desc());
      } else if ("month".equals(order)) {
        query.orderBy(salary.month.desc());
      } else if ("amount".equals(order)) {
        query.orderBy(salary.amount.desc());
      }
    }
    
    int limit = criteria.getItemsProPage() != null ? criteria.getItemsProPage() : 10; 
    query.limit(limit);
    
    return query.list(salary);
  }

}
