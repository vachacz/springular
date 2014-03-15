package com.github.springular.server.component.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.springular.server.component.employee.entity.SalaryBE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SalaryRepository extends JpaRepository<SalaryBE, Integer>, SalaryRepositoryCustom {

    @Query("select s from SalaryBE s where s.year = ?")
    public List<SalaryBE> findSalariesByYear(Integer year);

    public List<SalaryBE> findSalariesByYearAndMonth(Integer year, Integer month);

}
