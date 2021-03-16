package com.sahachko.hibernateConsoleProject.service;

import java.util.List;

import com.sahachko.hibernateConsoleProject.model.Region;

public interface RegionService {
	
	Region saveRegion(Region region);
	
	Region updateRegion(Region region);

	List<Region> getAllRegions();
	
	Region getRegionById(long id);
	
	void deleteRegionById(long id);
}
