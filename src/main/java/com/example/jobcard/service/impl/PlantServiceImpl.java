package com.example.jobcard.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jobcard.model.Plant;
import com.example.jobcard.repository.PlantRepository;
import com.example.jobcard.service.PlantService;


@Service
public class PlantServiceImpl implements PlantService{

	@Autowired
	private PlantRepository plantRepository;

	@Override
	public Plant savePlant(Plant plant) {

		return plantRepository.save(plant);
	}

	@Override
	public List<Plant> selctAllPlants() {

		return plantRepository.findAll();
	}

	@Override
	public Optional<Plant> selectPlantById(Long id) {

		return plantRepository.findById(id);
	}

	/*
	 * @Override public Plant updatePlantById(Plant plant, long id) { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public void deletePlantById(Long id) {
		
		plantRepository.deleteById(id);
		
	}

	@Override
	public List<Plant> findPlantsByMachinesId(Long id) {
	return plantRepository.findPlantsByMachinesId(id);
	}

	@Override
	public Plant findByPlantName(String plantName) {

		return plantRepository.findByPlantName(plantName) ;
	}
	
	@Override
	public Boolean existsById(Long id) {

		return plantRepository.existsById(id);
	}

	
}