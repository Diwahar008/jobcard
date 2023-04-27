package com.example.jobcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobcard.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{

	List<Plant> findPlantsByMachinesId(Long id);
	
	Plant findByPlantName(String PlantName);
	
	//findMachinesByOperationsId
}
 