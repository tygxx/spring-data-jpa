package com.example.jpa.springdatajpa;

import com.example.jpa.springdatajpa.simple.domain.JpaUser;
import com.example.jpa.springdatajpa.simple.service.JpaUserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleJpaTests {

    @Autowired
    private JpaUserService jpaUserService;

    @Test
    void contextLoads() {
        JpaUser jpaUser = new JpaUser();
        jpaUser.setName("张三");

        jpaUserService.insertUser(jpaUser);
        System.out.println(jpaUserService.findUserById(1L).toString());
    }

}
