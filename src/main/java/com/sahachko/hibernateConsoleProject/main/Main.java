package com.sahachko.hibernateConsoleProject.main;

import com.sahachko.hibernateConsoleProject.controller.PostController;
import com.sahachko.hibernateConsoleProject.controller.RegionController;
import com.sahachko.hibernateConsoleProject.controller.UserController;
import com.sahachko.hibernateConsoleProject.repository.PostRepository;
import com.sahachko.hibernateConsoleProject.repository.RegionRepository;
import com.sahachko.hibernateConsoleProject.repository.UserRepository;
import com.sahachko.hibernateConsoleProject.repository.hibernate.JavaIOPostRepository;
import com.sahachko.hibernateConsoleProject.repository.hibernate.JavaIORegionRepository;
import com.sahachko.hibernateConsoleProject.repository.hibernate.JavaIOUserRepository;
import com.sahachko.hibernateConsoleProject.service.PostService;
import com.sahachko.hibernateConsoleProject.service.RegionService;
import com.sahachko.hibernateConsoleProject.service.UserService;
import com.sahachko.hibernateConsoleProject.service.implementations.PostServiceImplementation;
import com.sahachko.hibernateConsoleProject.service.implementations.RegionServiceImplementation;
import com.sahachko.hibernateConsoleProject.service.implementations.UserServiceImplementation;
import com.sahachko.hibernateConsoleProject.view.MainView;
import com.sahachko.hibernateConsoleProject.view.PostView;
import com.sahachko.hibernateConsoleProject.view.RegionView;
import com.sahachko.hibernateConsoleProject.view.UserView;

public class Main {

	public static void main(String[] args) {
		RegionRepository regionRepo = new JavaIORegionRepository();
		RegionService regionService = new RegionServiceImplementation(regionRepo);
		RegionController regionController = new RegionController(regionService);
		RegionView regionView = new RegionView(regionController);
		
		PostRepository postRepo = new JavaIOPostRepository();
		PostService postService = new PostServiceImplementation(postRepo);
		PostController postController = new PostController(postService);
		PostView postView = new PostView(postController);
		
		UserRepository userRepo = new JavaIOUserRepository();
		UserService userService = new UserServiceImplementation(userRepo);
		UserController userController = new UserController(userService, regionService, postService);
		UserView userView = new UserView(userController);
		
		MainView mainView = new MainView(regionView, postView, userView);
		mainView.startConsole();
	}
	
}
