package com.example.jpa.springdatajpa.many.one.dao;

import com.example.jpa.springdatajpa.many.one.domain.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeDao extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

}