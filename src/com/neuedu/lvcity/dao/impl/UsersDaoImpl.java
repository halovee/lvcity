package com.neuedu.lvcity.dao.impl;

import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UsersDao;
import com.neuedu.lvcity.model.Users;

public class UsersDaoImpl implements UsersDao {

	Connection connection=null;
	public UsersDaoImpl(Connection connection) {
		this.connection=connection;
}
	@Override
	public Users login(String name ,String password) {
		Users user=null;
		PreparedStatement pStatement=null;
		ResultSet rSet=null;	
		try {
		String sql="select * from users where name=? and passwd= ? ";
		pStatement=(PreparedStatement) connection.prepareStatement(sql);
		pStatement.setString(1, name);
		pStatement.setString(2, password);
		
		//执行预编译SQL语句
		rSet = pStatement.executeQuery();
		 //遍历结果集并取出结果集的内容放入user对象
		if(rSet.next()){
			user = new Users();
			user.setId(rSet.getInt("id"));
			user.setName(rSet.getString("name"));
			user.setPasswd(rSet.getString("passwd"));
		}
		
		System.out.println(user.getName());
		System.out.println(user.getId());
		System.out.println(user.getPasswd());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//关闭结果集与语句对象
		DBUtils.closeStatement(rSet, pStatement);
	}
	//返回查询结果的user对象
	return user;
	}


	
}
