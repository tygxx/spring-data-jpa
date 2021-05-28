package com.example.jpa.springdatajpa.many.one.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import com.example.jpa.springdatajpa.many.one.dao.EmployeeDao;
import com.example.jpa.springdatajpa.many.one.domain.Employee;
import com.example.jpa.springdatajpa.specification.Specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Page<Employee> getTest() {
        // 查询参数
        // 员工名称
        String employeeName = "地";
        // 员工id
        List<Long> idsList = Arrays.asList(2L, 3L);
        // 员工注册日期
        Date startDate = new Date(1617354009000L);
        Date endDate = new Date();

        // 是否抓取部门
        Boolean fetchDepartment = false;
        Long departmentId = 1L;

        // 分页参数
        Integer page = 0;
        Integer size = 10;

        Sort sort = Sort.by(Direction.DESC, "id");

        PageRequest pageable = PageRequest.of(page, size, sort);// springData2.0之前 用new PageRequest(0,1)

        Specification<Employee> spec = Specifications.<Employee>and()
                .like(!StringUtils.isEmpty(employeeName), "empName", employeeName + "%")
                .in(!CollectionUtils.isEmpty(idsList), "id", idsList).ge(startDate != null, "createdDate", startDate)
                .lt(endDate != null, "createdDate", endDate).predicate(fetchDepartment, (root, query, cb) -> { // 有关联查询
                    Join department = (Join) root.fetch("department", JoinType.LEFT);
                    // if (Long.class != query.getResultType()) {
                    // department = (Join) root.fetch("department", JoinType.LEFT);
                    // } else {
                    // department = root.join("department", JoinType.LEFT);
                    // }

                    List<Predicate> predicates = new ArrayList<>();
                    // 有关联查询的条件
                    if (departmentId != null) {
                        predicates.add(cb.equal(department.get("id"), departmentId));
                    }
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }).build();

        Page<Employee> result = employeeDao.findAll(spec, pageable);
        System.out.println("总数:" + result.getTotalElements());
        // System.out.println("分页的size:" + result.getSize());
        return result;
    }
}