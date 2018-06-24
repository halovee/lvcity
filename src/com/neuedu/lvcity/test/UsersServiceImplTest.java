package com.neuedu.lvcity.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;
import com.neuedu.lvcity.service.impl.UsersServiceImpl;

public class UsersServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetInstance() {
		//调用service的方法
				UsersService usersService = UsersServiceImpl.getInstance();

				//返回值放在users对象
				Users users = usersService.login("admin", "123");
				System.out.println(users);
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

}
