package com.sahachko.hibernateConsoleProject.service.implementations;

import java.util.List;

import com.sahachko.hibernateConsoleProject.model.User;
import com.sahachko.hibernateConsoleProject.repository.UserRepository;
import com.sahachko.hibernateConsoleProject.service.UserService;


public class UserServiceImplementation implements UserService {
	private UserRepository repository;

	public UserServiceImplementation(UserRepository repository) {
		this.repository = repository;
	}
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public User getUserById(long id) {
		return repository.getById(id);
	}
	
	public User updateUser(User user) {
		return repository.update(user);
	}
	
	public List<User> getAllUsers() {
		return repository.getAll();
	}
	
	public void deleteUserById(long id) {
		repository.deleteById(id);
	}
}
