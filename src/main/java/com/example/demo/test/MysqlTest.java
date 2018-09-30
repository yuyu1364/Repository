package com.example.demo.test;



import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.DepartmentInterface;
import com.example.demo.dao.RoleInterface;
import com.example.demo.dao.UserInterface;
import com.example.demo.entiy.Deparment;
import com.example.demo.entiy.Role;
import com.example.demo.entiy.User;
import com.example.demo.transactManage.JpaConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {JpaConfiguration.class})
//@SpringBootTest
public class MysqlTest {
//	private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);
	
	@Autowired
	UserInterface userInterface;
	@Autowired
	DepartmentInterface departmentInterface;
	@Autowired
	RoleInterface roleInterface;
	
	@Before
	public void initData() {
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
//		assertNotNull(user);
		
		
	}
	@Test
	public void findPage() {
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
		Page<User> findAll = userInterface.findAll(pageable);
		for (User user : findAll) {
			System.out.println(user.getName()+"======"+user.getDeparment().getName()+"====="+user.getRole().get(0).getName());
		}
	}

}
