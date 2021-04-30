package com.example.jpa.springdatajpa;

import java.util.Date;
import java.util.Optional;

import com.example.jpa.springdatajpa.one.dao.IDCardDao;
import com.example.jpa.springdatajpa.one.dao.PersonDao;
import com.example.jpa.springdatajpa.one.domain.IDCard;
import com.example.jpa.springdatajpa.one.domain.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

/*
 *@Description: 一对一关系测试
 *@ClassAuthor: tengYong
 *@Date: 2021-04-30 12:33:51
*/
@SpringBootTest
class OneToOneJpaTests {

    @Autowired
    private IDCardDao idCardDao;

    @Autowired
    private PersonDao personDao;

    /**
     * 添加用户同时添加角色
     */
    @Test
    public void testAdd() {
        // 创建角色
        Person person = new Person();
        person.setBirthday(new Date());
        person.setName("张三");
        person.setSex("男");

        IDCard idCard = new IDCard();
        idCard.setIdNumber("431022199502367215");
        idCard.setCensusRegister("湖北省孝感市xxx县");
        idCard.setNation("汉");
        person.setIdCard(idCard);

        // 保存数据
        this.personDao.save(person);
    }

    /**
     * 根据用户ID查询用户,以及身份证信息
     */
    @Test
    public void testGetPeople() {
        Person personParam = new Person();
        personParam.setId(1L);
        Example<Person> example = Example.of(personParam);

        Optional<Person> peopleOptional = this.personDao.findOne(example);
        System.out.println("用户信息：" + peopleOptional.get());
        IDCard idCard = peopleOptional.get().getIdCard();
        System.out.println("身份证信息：" + idCard);

        // 用户信息：Person(id=1, name=张三, sex=男, birthday=2020-04-13 11:57:03.0)
        // 身份证信息：IDCard(id=1, idNumber=431022199502367215, nation=汉,
        // censusRegister=湖南省郴州市xxx县)
    }

    /**
     * 根据身份证id查询身份证信息,以及该身份证对对应的用户
     */
    @Test
    public void testGetAddress() {
        IDCard addressParam = new IDCard();
        addressParam.setId(1L);
        Example<IDCard> example = Example.of(addressParam);

        Optional<IDCard> idCardOptional = this.idCardDao.findOne(example);
        System.out.println("身份证信息：" + idCardOptional.get());
        // 身份证信息：IDCard(id=1, idNumber=431022199502367215, nation=汉,
        // censusRegister=湖南省郴州市xxx县)
        // 用户信息：Person(id=1, name=张三, sex=男, birthday=2020-04-13 11:57:03.0)
    }

}
