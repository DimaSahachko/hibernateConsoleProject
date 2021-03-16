package com.sahachko.hibernateConsoleProject.view;

import java.util.List;
import java.util.Scanner;

import com.sahachko.hibernateConsoleProject.controller.RegionController;
import com.sahachko.hibernateConsoleProject.model.Region;


public class RegionView {
	private RegionController controller;
	private Scanner sc = new Scanner(System.in);
	
	public RegionView(RegionController controller) {
		this.controller = controller;
	}
	
	public void startConsole() {
		System.out.println("Choose the available option. Type:");
		System.out.println("- \"add\" - to add a new region;");
		System.out.println("- \"update\" - to update the existing region;");
		System.out.println("- \"all\" - to print the list of all exising regions;");
		System.out.println("- \"get\" - to get the region by its id;");
		System.out.println("- \"delete\" - to delete the region by its id;");
		
		label:
		while(true) {
			String input = sc.nextLine();
				switch(input) {
					case "add" : 
						add();
						break;
					case "update" : 
						update();
						break;
					case "all" :
						allRegions();
						break;
					case "get" : 
						getRegionById();
						break;
					case "delete" :
						deleteRegion();
						break;
					default :
						System.out.println("The input is wrong. Type once again");
						continue label;
				}
			System.out.println("Do you want to continue with regions? (\"yes\"\\\"no\")");
			String doMore = sc.nextLine();
			if(doMore.equalsIgnoreCase("yes")) {
				startConsole();
				break;
			} else {
				break;
			}
		}		
	}
	
	private void add() {
		System.out.println("Type the name of the region");
		String name = sc.nextLine();
		Region region = controller.saveRegion(name);
		System.out.println("Region " + region + " was added");
	}
	
	private void update() {
		System.out.println("Type the id of the region which you want to update");
		try {
			Long id = Long.parseLong(sc.nextLine());
			System.out.println("Type the new name of the updating region");
			String name = sc.nextLine();
			Region region = controller.updateRegion(id, name);
			if(region == null) {
				System.out.println("There is no region with such id");
			} else {
				System.out.println(region + " updated");
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again.");
			update();
		}
	}
	
	private void allRegions() {
		List<Region> allRegions = controller.getAllRegions();
		if(allRegions.size() == 0) {
			System.out.println("There are no regions so far");
		}
		allRegions.stream().forEach(System.out::println);
	}
	
	private void getRegionById() {
		System.out.println("Type the id of the region which you want to get");
		try {
			long id = Long.parseLong(sc.nextLine());
			Region region = controller.getRegionById(id);
			if(region == null) {
				System.out.println("There is no region with such id");
			} else {
				System.out.println(region);
			}
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again.");
			getRegionById();
		}
	}
	
	private void deleteRegion() {
		System.out.println("Type the id of the region which you want to delete");
		try {
			long id = Long.parseLong(sc.nextLine());
			controller.deleteRegion(id);
		} catch (NumberFormatException exc) {
			System.out.println("Inappropriate input. Try once again.");
			deleteRegion();
		}
	}
}
