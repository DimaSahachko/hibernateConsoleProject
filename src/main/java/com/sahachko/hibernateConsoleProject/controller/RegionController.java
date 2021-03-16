package com.sahachko.hibernateConsoleProject.controller;

import java.util.List;

import com.sahachko.hibernateConsoleProject.model.Region;
import com.sahachko.hibernateConsoleProject.service.RegionService;


public class RegionController {
	private RegionService service;
	
	public RegionController(RegionService service) {
		this.service = service;
	}
	
	public Region saveRegion(String name) {
		Region region = new Region(name);
		return service.saveRegion(region);
	}
	
	public Region updateRegion(long id, String name) {
		Region region = new Region(name);
		region.setId(id);
		return service.updateRegion(region);
	}
	
	public List<Region> getAllRegions() {
		return service.getAllRegions();
	}
	
	public Region getRegionById(long id) {
		return service.getRegionById(id);
	}
	
	public void deleteRegion(long id) {
		service.deleteRegionById(id);
	}
}
