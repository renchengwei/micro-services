package com.xgg.microservices;

import com.xgg.microservices.dao.UserMapper;
import com.xgg.microservices.pojo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Service1ApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void contextLoads() {
		User user = userMapper.selectById(1);
	}

}
