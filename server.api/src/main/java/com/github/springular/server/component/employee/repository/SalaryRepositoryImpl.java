package com.github.springular.server.component.employee.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;
import com.github.springular.server.component.employee.entity.QEmployeeBE;
import com.github.springular.server.component.employee.entity.QSalaryBE;
import com.github.springular.server.component.employee.entity.SalaryBE;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;

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
      query.where(salary.amount.eq(criteria.getAmount()));
    }
    if (criteria.hasEmployeeFirstName()) {
      query.where(employee.firstName.containsIgnoreCase(criteria.getEmployeeFirstName()));
    }
    if (criteria.hasEmployeeLastName()) {
      query.where(employee.lastName.containsIgnoreCase(criteria.getEmployeeLastName()));
    }

    if (criteria.hasSpecifiedOrder()) {
      query.orderBy(computeOrderType(criteria.getOrderType()));
    }

    int limit = criteria.getItemsProPage() != null ? criteria.getItemsProPage() : 10;
    query.limit(limit);
    
    return query.list(salary);
  }

  private OrderSpecifier<?> computeOrderType(String order) {
    
    if ("firstName".equals(order)) return QEmployeeBE.employeeBE.firstName.asc();
    if ("lastName".equals(order)) return QEmployeeBE.employeeBE.lastName.asc();
    if ("year".equals(order)) return QSalaryBE.salaryBE.year.asc();
    if ("month".equals(order)) return QSalaryBE.salaryBE.month.asc();
    if ("amount".equals(order)) return QSalaryBE.salaryBE.amount.asc();
    
    throw new RuntimeException("Unknown order type.");
  }

}
