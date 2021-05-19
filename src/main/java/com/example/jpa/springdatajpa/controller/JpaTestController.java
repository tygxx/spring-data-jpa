package com.example.jpa.springdatajpa.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.example.jpa.springdatajpa.many.many.domain.Device;
import com.example.jpa.springdatajpa.many.many.domain.Group;
import com.example.jpa.springdatajpa.many.many.service.DeviceService;
import com.example.jpa.springdatajpa.many.many.service.GroupService;
import com.example.jpa.springdatajpa.many.one.domain.Department;
import com.example.jpa.springdatajpa.many.one.domain.Employee;
import com.example.jpa.springdatajpa.many.one.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "jpa测试")
@RestController
@RequestMapping("/jpa")
public class JpaTestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private GroupService groupService;

    @ApiOperation(value = "查询员工信息-manyToOne测试")
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Page<Employee> add() {
        Page<Employee> result = employeeService.getTest();
        List<Employee> resultList = result.getContent();
        for (Employee employee : resultList) {
            System.out.println("员工名称:" + employee.getEmpName());
            // 如果没有关联查询department时，序列化会报错，所以需要将其置为null
            employee.setDepartment(null);
            employee.setEmpJob(null);
            System.out.println("员工详情:" + JSONObject.toJSONString(employee));
            Department department = employee.getDepartment();
            System.out.println("部门:" + department != null ? JSONObject.toJSONString(department) : "找不到");
        }
        return result;
    }

    @ApiOperation(value = "查询设备-manyToMay测试")
    @RequestMapping(value = "/manay/device", method = RequestMethod.GET)
    public Device manyDevice() {
        Device device = deviceService.getDevice();
        return device;
    }

    @ApiOperation(value = "查询组-manyToMay测试")
    @RequestMapping(value = "/manay/group", method = RequestMethod.GET)
    public Group manyGroup() {
        Group group = groupService.getGroup();
        return group;
    }

}