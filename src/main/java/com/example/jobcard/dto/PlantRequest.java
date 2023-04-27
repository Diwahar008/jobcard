package com.example.jobcard.dto;

import java.util.Set;

public class PlantRequest {
	
	private String plantName;
	private String plantlocation;
	private Set<Long> machine;
	
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getPlantLocation() {
		return plantlocation;
	}
	public void setPlantLocation(String plantDesc) {
		this.plantlocation = plantDesc;
	}
	public Set<Long> getMachine() {
		return machine;
	}
	public void setMachine(Set<Long> machine) {
		this.machine = machine;
	}
	
	

}
