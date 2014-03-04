package com.github.springular.server.component.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.springular.server.component.employee.entity.SalaryBE;

public interface SalaryRepository extends JpaRepository<SalaryBE, Integer>, SalaryRepositoryCustom {

}
