package com.neuedu.lvcity.dao;

import com.neuedu.lvcity.model.Users;

public interface UsersDao {
	
	public Users login(String name, String password);
}
