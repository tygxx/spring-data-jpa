package com.example.jpa.springdatajpa.many.one.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import com.example.jpa.springdatajpa.base.BaseEntity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_dept") // 将实体和表进行映射,不然用@Query查询时会找不到对应关系
@org.hibernate.annotations.Table(appliesTo = "tb_dept", comment = "部门表") // 增加表注释
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate、@LastModifiedBy 、@LastModifiedDate才生效
public class Department extends BaseEntity {

    private static final long serialVersionUID = 1885381070041465437L;

    @Column(columnDefinition = "VARCHAR(64) unique comment '部门名称'")
    private String deptName;

    // @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch =
    // FetchType.EAGER) // 先保存targetEntity，设置外键后，再保存sourceEntity。
    // @JoinColumn(name = "dept_id", referencedColumnName = "id")
    // private Set<Employee> employees;

}