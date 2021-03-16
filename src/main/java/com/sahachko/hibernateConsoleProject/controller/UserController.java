package com.sahachko.hibernateConsoleProject.controller;

import java.util.List;
import com.sahachko.hibernateConsoleProject.model.*;
import com.sahachko.hibernateConsoleProject.service.*;

public class UserController {
	private UserService userService;
	private RegionService regionService;
	private PostService postService;
	
	public UserController(UserService userService, RegionService regionService, PostService postService) {
		this.userService = userService;
		this.regionService = regionService;
		this.postService = postService;
	}
	
	public User addUser(String firstName, String lastName, Region region, Role role, List<Post> posts) {
		User user = new User(firstName, lastName, region, role);
		user.setPosts(posts);
		return userService.saveUser(user);
	}
	
	public User updateUserFirstName(long id, String firstName) {
		User user = userService.getUserById(id);
		user.setFirstName(firstName);
		return userService.updateUser(user);
	}
	
	public User updateUserLastName(long id, String lastName) {
		User user = userService.getUserById(id);
		user.setLastName(lastName);
		return userService.updateUser(user);
	}
	
	public User updateUserPosts(long id, List<Post> posts) {
		User user = userService.getUserById(id);
		user.setPosts(posts);
		return userService.updateUser(user);
	}
	
	public User updateUserRegion(long id, Region region) {
		User user = userService.getUserById(id);
		user.setRegion(region);
		return userService.updateUser(user);
	}
	
	public User updateUserRole(long id, Role role) {
		User user = userService.getUserById(id);
		user.setRole(role);
		return userService.updateUser(user);
	}
	
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	public User getUserById(long id) {
		return userService.getUserById(id);
	}
	
	public void deleteUserById(long id) {
		userService.deleteUserById(id);
	}
	
	public List<Post> getAvailablePosts() {
		return postService.getAllPosts();
	}
	
	public Post getPostById(long id) {
		return postService.getPostById(id);
	}
	
	public List<Region> getAvailableRegions() {
		return regionService.getAllRegions();
	}
	
	public Region getRegionById(long id) {
		return regionService.getRegionById(id);
	}
}
