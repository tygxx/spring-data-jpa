package com.example.jpa.springdatajpa.many.many.service;

import com.example.jpa.springdatajpa.many.many.dao.DeviceDao;
import com.example.jpa.springdatajpa.many.many.domain.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceDao deviceDao;

    public Device getDevice() {
        Long id = 1L;
        Device device = deviceDao.getOne(id);
        return device;
    }
}