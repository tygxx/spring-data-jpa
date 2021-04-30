package com.example.jpa.springdatajpa.one.dao;

import com.example.jpa.springdatajpa.one.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonDao extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

}