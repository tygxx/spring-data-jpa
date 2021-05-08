package com.example.jpa.springdatajpa.many.one.dto;

import lombok.Getter;
import lombok.Setter;

/*
 *@Description: 部门查询条件
 *@ClassAuthor: tengYong
 *@Date: 2021-05-06 17:31:51
*/
@Getter
@Setter
public class DepartmentCondition {
    
    private String deptName;
    
    /**
	 * 查询的起始位置，默认值为0，相当于mysql的limit的第一个参数
	 */
	private Integer startPosition = 0;
	
	/**
	 * 查询的条数，默认值为100，相当于mysql的limit的第二个参数
	 */
	private Integer maxResult = 100;
}