package com.xgg.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
* @Description
* @Author  renchengwei
* @Date   2019-08-01
* @Param
* @Return      
* @Exception   
* 
*/
@SpringBootApplication
@EnableConfigurationProperties
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

}
