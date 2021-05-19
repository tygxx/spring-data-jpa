package com.example.jpa.springdatajpa.many.many.service;

import com.example.jpa.springdatajpa.many.many.dao.GroupDao;
import com.example.jpa.springdatajpa.many.many.domain.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    public Group getGroup() {
        Long id = 1L;
        Group group = groupDao.getOne(id);
        return group;
    }
}