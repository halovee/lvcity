package com.neuedu.lvcity.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import com.neuedu.lvcity.common.DBUtils;

public class DBUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		Connection conn= DBUtils.getConnection();
	}

}
