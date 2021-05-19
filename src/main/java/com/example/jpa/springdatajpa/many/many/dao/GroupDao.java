package com.example.jpa.springdatajpa.many.many.dao;

import com.example.jpa.springdatajpa.many.many.domain.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
 *@Description: 使用的是SpringDataJpa中自带的类库
 *@ClassAuthor: tengYong
 *@Date: 2021-05-07 12:58:58
*/
public interface GroupDao extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {

}