package com.example.jobcard.service;

import java.util.List;
import java.util.Optional;

import com.example.jobcard.model.Plant;


public interface PlantService {
	
	Plant savePlant(Plant plant);
	

	List<Plant> selctAllPlants();
	
	
	Optional<Plant> selectPlantById(Long id);
	
	
	//Plant updatePlantById(Plant plant,long id);
	
	
	void deletePlantById(Long id);
	
	
	List<Plant> findPlantsByMachinesId(Long id);
	
	
	Plant findByPlantName (String plantName);
	
	
	Boolean existsById(Long id);
	
	
}
