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
		
		//�õ�dao��ʵ����Ķ���
	//	UsersDao usersDao =new UsersDaoImpl(conn);
		//����dao��ķ���
	//	Users users = usersDao.login("admin", "123");
	//	System.out.println(users.getName());
	}

	@Test
	public void testLogin() {
		
		UsersDaoImpl udi=new UsersDaoImpl(conn);
		udi.login("admin", "123");
		
	}

}
