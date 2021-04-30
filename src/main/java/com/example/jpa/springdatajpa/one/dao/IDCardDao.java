package com.example.jpa.springdatajpa.one.dao;

import com.example.jpa.springdatajpa.one.domain.IDCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDCardDao extends JpaRepository<IDCard, Long>, JpaSpecificationExecutor<IDCard> {

}