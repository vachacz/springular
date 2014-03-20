package com.github.springular.server.component.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.springular.server.component.employee.entity.EmployeeBE;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository extends JpaRepository<EmployeeBE, Integer> {

    EmployeeBE findByLogin(String login);
}