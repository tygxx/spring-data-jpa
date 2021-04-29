package com.example.jpa.springdatajpa.user.dao;

import com.example.jpa.springdatajpa.user.domain.JpaUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {

}