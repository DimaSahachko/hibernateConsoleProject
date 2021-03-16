package com.sahachko.hibernateConsoleProject.view;

import java.util.List;
import java.util.Scanner;

import com.sahachko.hibernateConsoleProject.controller.PostController;
import com.sahachko.hibernateConsoleProject.model.Post;


public class PostView {
	private PostController controller;
	private Scanner sc = new Scanner(System.in);
	
	public PostView(PostController controller) {
		this.controller = controller;
	}
	
	public void startConsole() {
		System.out.println("Choose the available option. Type:");
		System.out.println("- \"add\" - to add a new post;");
		System.out.println("- \"update\" - to update the existing post;");
		System.out.println("- \"all\" - to print the list of all existing posts");
		System.out.println("- \"get\" - to get the post by its id;");
		System.out.println("- \"delete\" - to delete the post by its id");
		
		label:
		while(true) {
			String input = sc.nextLine();
			switch(input) {
			case "add" :
				addPost();
				break;
			case "update" :
				updatePost();
				break;
			case "all" :
				allPosts();
				break;
			case "get" :
				getPostById();
				break;
			case "delete" :
				deletePost();
				break;
			default :
				System.out.println("The input is wrong. Type once again.");
				continue label;
			}
			System.out.println("Do you want to continue with posts? (\"yes\"\\\"no\")");
			String doMore = sc.nextLine();
			if(doMore.equalsIgnoreCase("yes")) {
				startConsole();
				break;
			} else {
				break;
			}
		}
	}
	
	private void addPost() {
		System.out.println("Type the post's content");
		String content = sc.nextLine();
		Post post = controller.savePost(content);
		System.out.println("Post \"" + post + "\" was added");
	}
	
	private void updatePost() {
		System.out.println("Type the id of the post which you want to update");
		try {
			long id = Long.parseLong(sc.nextLine());
			System.out.println("Type the new content of the updating post");
			String content = sc.nextLine();
			Post post = controller.updatePost(id, content);
			if(post == null) {
				System.out.println("There is no post with wuch id");
			} else {
				System.out.println(post);
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again");
			updatePost();
		}
	}
	
	private void allPosts() {
		List<Post> allPosts = controller.getAllPosts();
		if(allPosts.size() == 0) {
			System.out.println("There are no posts so far");
		}
		allPosts.stream().forEach(System.out::println);
	}
	
	private void getPostById() {
		System.out.println("Type the id of the post which you want to get");
		try {
			long id = Long.parseLong(sc.nextLine());
			Post post = controller.getPostById(id);
			if(post == null) {
				System.out.println("There is no post with such id");
			} else {
				System.out.println(post);
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropraite input. Try once again");
			getPostById();
		}
	}
	
	private void deletePost() {
		System.out.println("Type the id of the post which you want to delete");
		try {
			long id = Long.parseLong(sc.nextLine());
			controller.deletePostById(id);
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again");
			deletePost();
		}
	}
}
