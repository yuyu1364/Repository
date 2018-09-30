package com.example.demo.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.entiy.Deparment;
import com.example.demo.entiy.Role;
import com.example.demo.entiy.User;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= {RedisConfig.class,UserRedis.class})
@SpringBootTest
public class RedisTest {
	
	@Autowired
	UserRedis userRedis;
	
	@Before
	public void setup() {
		Deparment deparment = new Deparment();
		deparment.setName("开发部");
		
		Role role = new Role();
		role.setName("admin");
		
		User user = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDeparment(deparment);
		List<Role> list = new ArrayList<>();
		list.add(role);
		user.setRole(list);
		userRedis.delete(this.getClass().getName()+"userByname:"+user.getName());
		userRedis.add(this.getClass().getName()+"userByname:"+user.getName(), 10L, user);
	}
	@Test
	public void getname() {
		User user = userRedis.get(this.getClass().getName()+"userByname:user");
		Assert.assertNotNull(user);
		System.out.println("用户名"+user.getName()+"=====部门："+user.getDeparment().getName());
		
	}
}
