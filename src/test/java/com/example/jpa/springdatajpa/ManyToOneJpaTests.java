package com.example.jpa.springdatajpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.jpa.springdatajpa.many.one.dao.DepartmentDao;
import com.example.jpa.springdatajpa.many.one.dao.EmployeeDao;
import com.example.jpa.springdatajpa.many.one.domain.Department;
import com.example.jpa.springdatajpa.many.one.domain.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/*
 *@Description: 多对一关系测试
 *@ClassAuthor: tengYong
 *@Date: 2021-04-30 12:33:51
*/
@SpringBootTest
class ManyToOneJpaTests {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 添加部门
     */
    @Test
    public void testDepartmentAdd() {
        Department department = new Department();
        department.setDeptName("市场部");
        departmentDao.save(department);
    }

    /**
     * 添加员工并设置外键
     */
    @Test
    public void testEmployeeAdd() {
        Employee employee = new Employee();
        employee.setEmpName("王九");
        employee.setEmpJob("拓展市场");

        Department department = departmentDao.findByDeptName("市场部");
        if (department == null) {
            System.out.println("部门id不存在");
            return;
        }
        employee.setDepartment(department);
        employeeDao.save(employee);
    }

    /**
     * 查询部门
     */
    @Test
    public void testFindDepartment() {
        // Optional<Department> departmentOptional = departmentDao.findById(1L);
        // System.out.println("部门:" + departmentOptional.get());
        Department department = departmentDao.findByDeptNameQ("市场部");
        System.out.println("部门:" + department.toString());
    }

    /**
     * 查询员工所属部门
     */
    @Test
    public void testFindEmployeeById() {
        Optional<Employee> employeeOptional = employeeDao.findById(2L);

        System.out.println("员工:" + employeeOptional.get());
        System.out.println("所属部门:" + employeeOptional.get().getDepartment());// 懒加载
    }

    /**
     * 动态查询
     */
    @Test
    public void dyFind() {
        // 查询参数
        String employeeName = "";

        // 分页参数
        Integer page = 0;
        Integer size = 10;

        Sort.Order orders = new Sort.Order(Sort.Direction.DESC, "createdDate");
        Sort sort = Sort.by(orders);

        Pageable pageable = PageRequest.of(page, size, sort);// springData2.0之前 用new PageRequest(0,1)

        // 构建Specification,通过JpaSpecificationExecutor查询时的一个入参
        Specification<Employee> spec = new Specification<Employee>() {
            /**
             * @param root:           将User 对象映射为 Root 可以通过它的对象获取对象中的各种字段
             * @param criteriaQuery   ：查询条件的容器，存放查询条件;存放单个条件或者条件数组
             * @param criteriaBuilder ：构造查询条件，大于/小于/等于/like，比较对象
             * @return predicate :定义查询条件；条件和比较的对象
             */
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(employeeName)) {
                    predicates.add(criteriaBuilder.like(root.get("employeeName"), "%" + employeeName + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        Page<Employee> result = employeeDao.findAll(spec, pageable);
        List<Employee> resultList = result.getContent();
        for (Employee employee : resultList) {
            System.out.println(employee.getEmpName());
        }
    }

}
