package com.example.jpa.springdatajpa.simple.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
// 定义表名
@Table(name = "jpa_user")
// 增加表注释
@org.hibernate.annotations.Table(appliesTo = "jpa_user", comment = "用户表")
// 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate
// 、@LastModifiedBy 、@LastModifiedDate才生效
@EntityListeners(AuditingEntityListener.class)
public class JpaUser {

    @Id
    // GenerationType.AUTO(主键由程序控制, 默认值)策略会额外创建一张hibernate_sequences序列表
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 comment '用户名'")
    private String name;

    @Column(columnDefinition = "datetime comment '创建时间'")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;

    @Column(columnDefinition = "datetime comment '更新时间'")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdatedDate;

    // @Column
    // @Version
    // private Long objectVersion;
}