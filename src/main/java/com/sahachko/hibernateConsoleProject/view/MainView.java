package com.sahachko.hibernateConsoleProject.view;

import java.util.Scanner;

import com.sahachko.hibernateConsoleProject.repository.ConnectionUtils;



public class MainView {
	private RegionView regionView;
	private PostView postView;
	private UserView userView;
	Scanner sc = new Scanner(System.in);
	
	public MainView(RegionView regionView, PostView postView, UserView userView) {
		this.regionView = regionView;
		this.postView = postView;
		this.userView = userView;
	}
	
	public void startConsole() {
		System.out.println("What do you want to work with?");
		System.out.println("- \"regions\" - to work with regions;");
		System.out.println("- \"posts\" - to work with posts;");
		System.out.println("- \"users\" - to work with users");
		label:
			while(true) {
				String input = sc.nextLine();
				switch(input) {
				case "regions" :
					regionView.startConsole();
					break;
				case "posts" :
					postView.startConsole();
					break;
				case "users" :
					userView.startConsole();
					break;
				default :
					System.out.println("The input is wrong. Type once again.");
					continue label;
				}
				System.out.println("Do you want to continue working with program?");
				String doMore = sc.nextLine();
				if(doMore.equalsIgnoreCase("yes")) {
					startConsole();
					break;
				} else {
					System.out.println("Have a nice day. Good buy");
					if(sc != null) {
						sc.close();
					}
					ConnectionUtils.closeSessionFactory();
					break;
			}
		}			
	}
}
