package com.github.springular.server.component.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.springular.server.component.employee.entity.EmployeeBE;

public interface EmployeeRepository extends JpaRepository<EmployeeBE, Integer> {
  
}
