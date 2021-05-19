package com.example.jpa.springdatajpa.many.many.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.jpa.springdatajpa.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_device") // 将实体和表进行映射,不然用@Query查询时会找不到对应关系
@org.hibernate.annotations.Table(appliesTo = "m_device", comment = "设备表") // 增加表注释
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate、@LastModifiedBy 、@LastModifiedDate才生效
public class Device extends BaseEntity {
    
    private static final long serialVersionUID = 7521231479834055008L;

    @Column(columnDefinition = "int(64) comment '设备二级分类id'")
    private Integer categoryId;

    @Column(columnDefinition = "int(64) comment '设备源id'")
    private Integer sourceId;

    @Column(columnDefinition = "VARCHAR(64) comment '设备名称'")
    private String name;

    @Column(columnDefinition = "int(8) comment '设备类型'")
    private Integer type;

    @Column(columnDefinition = "text comment '设备描述'")
    private String description;

    @Column(columnDefinition = "int(8) comment '设备实例化状态'")
    private Integer status;

    @JsonIgnoreProperties(value = {"deviceList"})
    @ManyToMany(mappedBy = "deviceList",fetch = FetchType.LAZY)
    private List<Group> groupList;
}