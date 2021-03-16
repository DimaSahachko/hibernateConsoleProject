package com.sahachko.hibernateConsoleProject.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sahachko.hibernateConsoleProject.controller.UserController;
import com.sahachko.hibernateConsoleProject.model.Post;
import com.sahachko.hibernateConsoleProject.model.Region;
import com.sahachko.hibernateConsoleProject.model.Role;
import com.sahachko.hibernateConsoleProject.model.User;


public class UserView {
	private UserController controller;
	private Scanner sc = new Scanner(System.in);
	
	public UserView(UserController controller) {
		this.controller = controller;
	}
	
	public void startConsole() {
		System.out.println("Choose the available option. Type:");
		System.out.println("- \"add\" - to add a new user;");
		System.out.println("- \"update\" - to update the existing user;");
		System.out.println("- \"all\" - to print the list of all existing users");
		System.out.println("- \"get\" - to get the user by their id;");
		System.out.println("- \"delete\" - to delete the user by their id");
		
		label:
		while(true) {
			String input = sc.nextLine();
			switch(input) {
			case "add" :
				addUser();
				break;
			case "update" :
				updateUser();
				break;
			case "all" :
				allUsers();
				break;
			case "get" :
				getUserById();
				break;
			case "delete" :
				deleteUser();
				break;
			default :
				System.out.println("The input is wrong. Type once again.");
				continue label;
			}
			System.out.println("Do you want to continue with users? (\"yes\"\\\"no\")");
			String doMore = sc.nextLine();
			if(doMore.equalsIgnoreCase("yes")) {
				startConsole();
				break;
			} else {
				break;
			}
		}
	}
	
	private void addUser() {
		String firstName = setUserFirstName();
		String lastName = setUserLastName();
		Region region = setUserRegion();
		Role role = setUserRole();
		List<Post> userPosts = new ArrayList<>();
		System.out.println("Do you want to add posts to this user?");
		label:
			while(true) {
				String input = sc.nextLine();
				switch(input) {
				case "yes" :
				case "Yes" :
					userPosts = setUserPosts();
					break;
				case "no" :
				case "No" :
					break;
				default:
					System.out.println("Inappropriate input. Just type \"yes\" or \"no\"");
					continue label;
				}
			break;
			}
		User user = controller.addUser(firstName, lastName, region, role, userPosts);
		System.out.println("User was added\n" + user);
	}
	
	private String setUserFirstName() {
		System.out.println("Type the user's first name");
		String firstName = sc.nextLine();
		return firstName;
	}
	
	private String setUserLastName() {
		System.out.println("Type the user's last name");
		String lastName = sc.nextLine();
		return lastName;
	}
	
	private Region setUserRegion() {
		System.out.println("Choose the user's region from this list");
		Region region = null;
		List<Region> regions = controller.getAvailableRegions();
		regions.stream().forEach(System.out::println);
		label:
		while(true) {
			System.out.println("Just type the region's id");
			try {
				long id = Long.parseLong(sc.nextLine());
				region = controller.getRegionById(id);
				if(region == null) {
					System.out.println("There is no region with such id. Choose another one.");
					continue label;
				} else {
					System.out.println("Region " + region + " was added");
				}
			} catch(NumberFormatException exc) {
				System.out.println("Inappropriate input. Try once again");
				continue label;
			}
			break;
		}
		return region;
	}
	
	private Role setUserRole() {
		Role userRole = null;
		System.out.println("Choose the available roles: ");
		Role[] roles = Role.values();
		for(Role role : roles) {
			System.out.println(role);
		}
		label:
		while(true) {
			String input = sc.nextLine();
			switch(input) {
			case "admin" : // break was missed intentionally;
			case "Admin" :
			case "ADMIN" :
				userRole = Role.ADMIN;
				break;
			case "moderator" : // break was missed intentionally;
			case "Moderator" :
			case "MODERATOR" :
				userRole = Role.MODERATOR;
				break;
			case "user" : // break was missed intentionally;
			case "User" :
			case "USER" :
				userRole = Role.USER;
				break;
			default:
				System.out.println("The input is wrong. Try once again.");
				continue label;
			}
			break;
		}
		System.out.println("Role " + userRole + " was added.");
		return userRole;
	}
	
	private List<Post> setUserPosts() {
		System.out.println("Form user's posts list. Choose from the available posts. ");
		List<Post> userPosts = new ArrayList<>();
		List<Post> allPosts = controller.getAvailablePosts();
		allPosts.stream().forEach(System.out::println);
		label:
		while(true) {
			System.out.println("Just type the id of the post which you want to add");
			try {
				long id = Long.parseLong(sc.nextLine());
				Post post = controller.getPostById(id);
				if(post == null) {
					System.out.println("There is no post with such id");
				} else {
					userPosts.add(post);
					System.out.println("Post \"" + post + "\" was added");
				}
			} catch (NumberFormatException exc) {
				System.out.println("Inappropriate input. Try once again");
				continue label;
			}
			System.out.println("Add another post? (\"yes\"\\\"no\")");
			String addAnotherPost = sc.nextLine();
			if(!(addAnotherPost.equalsIgnoreCase("yes"))) {
				break;
			}
		}
		return userPosts;
	}
	
	private void updateUser() {
		User user = null;
		System.out.println("Type the id of the user which you want to update");
		long id = 0;
		try {
			id = Long.parseLong(sc.nextLine());
			user = controller.getUserById(id);
			if (user == null) {
				System.out.println("There is no user with such id. Choose another one");
				updateUser();
				return;
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again");
			updateUser();
			return;
		}
		
		label:
			while(true) {
				System.out.println("What do you want to update? Type:");
				System.out.println("- \"first name\" - to update first name;");
				System.out.println("- \"last name\" - to update last name;");
				System.out.println("- \"posts\" - to update posts;");
				System.out.println("- \"region\" - to update region.");
				System.out.println("- \"role\" - to update role.");
				String input = sc.nextLine();
				switch(input) {
				case "first name" :
					String firstName = setUserFirstName();
					user = controller.updateUserFirstName(id, firstName);
					System.out.println("User was updated\n" + user);
					break;
				case "last name" :
					String lastName = setUserLastName();
					user = controller.updateUserLastName(id, lastName);
					System.out.println("User was updated\n" + user);
					break;
				case "posts" :
					List<Post> posts = setUserPosts();
					user = controller.updateUserPosts(id, posts);
					System.out.println("User was updated\n" + user);
					break;
				case "region" :
					Region region = setUserRegion();
					user = controller.updateUserRegion(id, region);
					System.out.println("User was updated\n" + user);
					break;
				case "role" :
					Role role = setUserRole();
					user = controller.updateUserRole(id, role);
					System.out.println("User was updated\n" + user);
				default:
					System.out.println("The input is wrong. Try once again");
					continue label;
				}	
				System.out.println("Do you want to update anything else of this user? (\"yes\"/\"no\")");
				String doMore = sc.nextLine();
				if(doMore.equalsIgnoreCase("yes")) {
					continue label;
				} else {
					break;
				}
			}
	}
	
	private void allUsers() {
		List<User> allUsers = controller.getAllUsers();
		if(allUsers.size() == 0) {
			System.out.println("There are no users so far");
		}
		allUsers.stream().forEach(System.out::println);
	}
	
	private void getUserById() {
		System.out.println("Type the id of the user which you want to get");
		try {
			long id = Long.parseLong(sc.nextLine());
			User user = controller.getUserById(id);
			if(user == null) {
				System.out.println("There is no user with such id");
			} else {
				System.out.println(user);
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropraite input. Try once again");
			getUserById();
		}
	}
	
	private void deleteUser() {
		System.out.println("Type the id of the user which you want to delete");
		try {
			long id = Long.parseLong(sc.nextLine());
			controller.deleteUserById(id);
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again");
			deleteUser();
		}
	}
}
