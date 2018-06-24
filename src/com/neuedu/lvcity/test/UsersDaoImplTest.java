package com.neuedu.lvcity.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UsersDao;
import com.neuedu.lvcity.dao.impl.UsersDaoImpl;
import com.neuedu.lvcity.model.Users;

public class UsersDaoImplTest {
	Connection conn = DBUtils.getConnection();
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testUsersDaoImpl() {
		
		//拿到dao层实现类的对象
	//	UsersDao usersDao =new UsersDaoImpl(conn);
		//调用dao层的方法
	//	Users users = usersDao.login("admin", "123");
	//	System.out.println(users.getName());
	}

	@Test
	public void testLogin() {
		
		UsersDaoImpl udi=new UsersDaoImpl(conn);
		udi.login("admin", "123");
		
	}

}
