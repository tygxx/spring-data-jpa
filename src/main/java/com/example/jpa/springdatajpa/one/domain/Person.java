package com.example.jpa.springdatajpa.one.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.jpa.springdatajpa.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "t_person")
@Table(name = "t_person") // 将实体和表进行映射,不然用@Query查询时会找不到对应关系
@org.hibernate.annotations.Table(appliesTo = "t_person", comment = "人表") // 增加表注释
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate、@LastModifiedBy 、@LastModifiedDate才生效
@EqualsAndHashCode(exclude = { "idCard" }) // 不重写当前类的idCard字段equals()和HashCode()方法,否则会因为循环依赖保存
@ToString(exclude = { "idCard" }) // 不重写当前类的idCard字段ToString()方法，,否则会因为循环依赖保存
public class Person extends BaseEntity{

    private static final long serialVersionUID = 7622091647759263713L;

    @Column(columnDefinition = "VARCHAR(64) CHARACTER SET utf8mb4 comment '名字'")
    private String name;

    @Column(columnDefinition = "VARCHAR(1) comment '性别'")
    private String sex;

    @Column(columnDefinition = "date comment '出生日期'")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL) // Person是关系的维护端，当删除 Person，会级联删除 IDCard
    @JoinColumn(name = "card_id", referencedColumnName = "id", columnDefinition = "bigint(32) comment '身份证id'") // Person表中的card_id字段参考IDCard表中的id字段,会在t_person表多生成一个card_id字段
    private IDCard idCard;// 身份证
}