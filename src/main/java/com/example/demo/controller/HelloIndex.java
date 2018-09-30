package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DepartmentInterface;
import com.example.demo.dao.RoleInterface;
import com.example.demo.dao.UserInterface;
import com.example.demo.entiy.Deparment;
import com.example.demo.entiy.Role;
import com.example.demo.entiy.User;

@RestController
@RequestMapping("hello")
public class HelloIndex {

	
	@Autowired
	UserInterface userInterface;
	@Autowired
	DepartmentInterface departmentInterface;
	@Autowired
	RoleInterface roleInterface;
	
	
	@RequestMapping("index")
	public String index() {
		TestRun run = new TestRun();
		Thread thread = new Thread(run);
		Thread thread1 = new Thread(run);
		thread.start();
		thread1.start();
		System.out.println("你是的");
		return "nihao";
	}
	
	@RequestMapping("add")
	public User add() {
		userInterface.deleteAll();
		roleInterface.deleteAll();
		departmentInterface.deleteAll();
		
		Deparment dep = new Deparment();
		dep.setName("开发部");
		departmentInterface.save(dep);
//		assertNotNull(dep.getId());
		
		Role role = new Role();
		role.setName("admin");
		roleInterface.save(role);
//		assertNotNull(role.getId());
		
		User user  = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDeparment(dep);
		List<Role> roles = roleInterface.findAll();
//		assertNotNull(roles);
		user.setRole(roles);
		userInterface.save(user);
		
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
		Page<User> findAll = userInterface.findAll(pageable);
		User user2 = new User();
		for (User user1 : findAll) {
			user2 = user1;
			System.out.println(user.getName()+"======"+user.getDeparment().getName()+"====="+user.getRole().get(0).getName());
		}
		return user2;
	}
	public static void main(String[] args) {
		TestRun run = new TestRun();
		Thread thread = new Thread(run);
		thread.start();
		System.out.println("你是的");
	}
	
}
