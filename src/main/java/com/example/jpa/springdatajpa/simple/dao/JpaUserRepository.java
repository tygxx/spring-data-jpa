package com.example.jpa.springdatajpa.simple.dao;

import com.example.jpa.springdatajpa.simple.domain.JpaUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {

}