package com.xgg.microservices;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xgg.microservices.dao")
/**
* @Description
* @Author  renchengwei
* @Date   2019-08-01
* @Param
* @Return      
* @Exception   
* 
*/
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

}
