package com.sahachko.hibernateConsoleProject.service;

import java.util.List;

import com.sahachko.hibernateConsoleProject.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	User getUserById(long id);
	
	User updateUser(User user);
	
	List<User> getAllUsers();
	
	void deleteUserById(long id);
}
