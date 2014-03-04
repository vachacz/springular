package com.github.springular.server.component.employee.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.github.springular.server.component.employee.SalaryQueryCriteriaDO;
import com.github.springular.server.component.employee.entity.EmployeeBE;
import com.github.springular.server.component.employee.entity.SalaryBE;

public class SalaryRepositoryImpl implements SalaryRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;
  
  @Override
  public List<SalaryBE> filterByCriteria(SalaryQueryCriteriaDO criteria) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<SalaryBE> query = cb.createQuery(SalaryBE.class);
    
    Root<SalaryBE> salary = query.from(SalaryBE.class);
    Join<SalaryBE, EmployeeBE> employee = salary.join("employee");
    
    List<Predicate> predicates = new ArrayList<Predicate>();
    
    if (criteria.getMonth() != null) {
      predicates.add( cb.equal(salary.get("month"), criteria.getMonth()) );
    }
    if (criteria.getYear() != null) {
      predicates.add( cb.equal(salary.get("year"), criteria.getYear()) );
    }
    if (criteria.hasAmount()) {
      predicates.add( cb.equal(salary.get("amount"), Integer.valueOf(criteria.getAmount())) );
    }
    if (criteria.hasEmployeeLastName()) {
      predicates.add( cb.like(cb.lower(employee.<String>get("lastName")), "%" + criteria.getEmployeeLastName().toLowerCase() + "%"));
    }
    if (criteria.hasEmployeeFirstName()) {
      predicates.add( cb.like(cb.lower(employee.<String>get("firstName")), "%" + criteria.getEmployeeFirstName().toLowerCase() + "%"));
    }
    
    // TODO order by
    // TODO limit results
    
    CriteriaQuery<SalaryBE> resultQuery = query.select(salary).where(predicates.toArray(new Predicate[predicates.size()]));
    return entityManager.createQuery(resultQuery).getResultList();    
  }

}
