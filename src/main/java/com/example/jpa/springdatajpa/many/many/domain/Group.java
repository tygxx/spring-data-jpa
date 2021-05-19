package com.example.jpa.springdatajpa.many.many.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.jpa.springdatajpa.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_group") // 将实体和表进行映射,不然用@Query查询时会找不到对应关系
@org.hibernate.annotations.Table(appliesTo = "m_group", comment = "组表") // 增加表注释
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate、@LastModifiedBy 、@LastModifiedDate才生效
public class Group extends BaseEntity {
    
    private static final long serialVersionUID = -4232801961819988581L;
    
    @Column(columnDefinition = "int(64) comment '设备二级分类id'")
    private Integer categoryId;

    @Column(columnDefinition = "VARCHAR(64) comment '分组名'")
    private String name;

    @JsonIgnoreProperties(value = {"groupList"})
    @ManyToMany(fetch = FetchType.LAZY)
    // joinColumns：JoinColumn-当前类主键, inverseJoinColumns-关联对象主键
    // 关系维护端，负责多对多关系的绑定和解除
    // @JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(group_id), 要关联的关系被维护端(device_id)
    @JoinTable(name = "m_group_device", joinColumns = {@JoinColumn(name = "m_group_id", columnDefinition = "bigint(32) comment '组id'")}, inverseJoinColumns = {
                @JoinColumn(name = "m_device_id", columnDefinition = "bigint(32) comment '设备id'")})
    private List<Device> deviceList;
}