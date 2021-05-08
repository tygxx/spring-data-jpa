package com.example.jpa.springdatajpa;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Module hibernate5Module() {
        Hibernate5Module module = new Hibernate5Module();
        // 禁用(表示要忽略@Transient字段属性,默认为true,设置为false禁用)
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        // 延时加载的对象不使用时设置为null
        module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        return module;
    }

    // @Bean
    // public ObjectMapper objectMapper() {
    // ObjectMapper objectMapper = new ObjectMapper();
    // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
    // false);
    // Hibernate5Module hibernate5Module = new Hibernate5Module();
    // hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
    // objectMapper.registerModule(hibernate5Module);
    // objectMapper.setSerializationInclusion(Include.NON_NULL);
    // return objectMapper;
    // }

}