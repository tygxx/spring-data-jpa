package com.example.jpa.springdatajpa.user.service;

import java.util.List;

import com.example.jpa.springdatajpa.user.dao.JpaUserRepository;
import com.example.jpa.springdatajpa.user.domain.JpaUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaUserService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    public JpaUser insertUser(JpaUser user) {
        return jpaUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        jpaUserRepository.deleteById(id);
    }

    public JpaUser updateUser(JpaUser user) {
        return jpaUserRepository.save(user);
    }

    public List<JpaUser> findAllUser() {
        return jpaUserRepository.findAll();
    }

    public JpaUser findUserById(Long id) {
        return jpaUserRepository.findById(id).orElse(null);
    }
}