package com.example.jpa.springdatajpa.many.one.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.jpa.springdatajpa.base.BaseEntity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_emp") // 将实体和表进行映射,不然用@Query查询时会找不到对应关系
@org.hibernate.annotations.Table(appliesTo = "tb_emp", comment = "员工表") // 增加表注释
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate、@LastModifiedBy 、@LastModifiedDate才生效
// @EqualsAndHashCode(exclude = { "department" }) // 不使用employees字段参与hashCode/equals的重写
// @ToString(exclude = { "department" }) // 不使用employees字段参与toString的重写
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 3454573140752653513L;

    @Column(columnDefinition = "VARCHAR(64) unique comment '名字'")
    private String empName;

    @Column(columnDefinition = "VARCHAR(64) comment '职位'")
    private String empJob;

    @ManyToOne(fetch = FetchType.LAZY)
    // targetEntity：先保存targetEntity，设置外键后，再保存sourceEntity。
    // FetchType.LAZY: 开启懒加载 需要在配置文件配置
    // spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
    @JoinColumn(name = "dept_id", columnDefinition = "bigint(32) comment '部门id'") // 会自动根据配置在数据表生成dept_id字段
    private Department department;
}