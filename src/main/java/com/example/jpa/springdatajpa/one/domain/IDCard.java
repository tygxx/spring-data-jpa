package com.example.jpa.springdatajpa.one.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import com.example.jpa.springdatajpa.base.BaseEntity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_id_card") // 将实体和表进行映射,不然用@Query查询时会找不到对应关系
@org.hibernate.annotations.Table(appliesTo = "t_id_card", comment = "身份表") // 增加表注释
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 有了@EntityListeners(AuditingEntityListener.class)这个注解，@CreatedBy、@CreatedDate、@LastModifiedBy 、@LastModifiedDate才生效
@EqualsAndHashCode(exclude = { "person" }) // 不重写当前类的idCard字段equals()和HashCode()方法,否则会因为循环依赖保存
@ToString(exclude = { "person" }) // 不重写当前类的idCard字段ToString()方法，,否则会因为循环依赖保存
public class IDCard extends BaseEntity{

    private static final long serialVersionUID = -2637001766227416537L;

    @Column(columnDefinition = "VARCHAR(32) comment '身份证号'")
    private String idNumber;

    @Column(columnDefinition = "VARCHAR(32) comment '民族'")
    private String nation;

    @Column(columnDefinition = "VARCHAR(128) comment '户籍地址'")
    private String censusRegister;

    // mappedBy的意思就是“被映射”，即mappedBy这方不用管关联关系，关联关系交给另一方处理
    // optional=true设置person属性是否可以为null
    // 只在一边维护关系
    // @OneToOne(mappedBy = "idCard")
    // private Person person;
}