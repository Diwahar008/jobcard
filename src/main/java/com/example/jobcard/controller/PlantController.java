package com.example.jobcard.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.jobcard.dto.PlantRequest;
import com.example.jobcard.exception.ResourceNotFoundException;
import com.example.jobcard.model.Machine;
import com.example.jobcard.model.Plant;
import com.example.jobcard.service.MachineService;
import com.example.jobcard.service.PlantService;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/plant")
public class PlantController {
	
	@Autowired
	private PlantService plantService;
	
	@Autowired
	private MachineService machineService;
	
	@PostMapping("/plant")
	public ResponseEntity<Plant> createPlant(@RequestBody Plant plant){
		
		return ResponseEntity.ok(plantService.savePlant(plant));
	}
	
	public ResponseEntity<?> savePlant(@RequestBody PlantRequest plantrequest){
		
		Plant plant = new Plant(plantrequest.getPlantName(),plantrequest.getPlantLocation());
		Set<Long> plantBymachine = plantrequest.getMachine();
		Set<Machine> machines = new HashSet<>();
		if(plantBymachine == null) {
			System.out.println("OPERATION ARE NULL");
		}
		else {
			plantBymachine.forEach(machine ->{
				Machine findmachine = machineService.selectMachineById(machine).orElseThrow(()
						-> new ResourceNotFoundException("Machine", "String", machines));
				machines.add(findmachine);
			});
			plant.setMachines(machines);
			plantService.savePlant(plant);
		}
		return ResponseEntity.ok(new MessageResponse("PLANT SAVED SUCCESSFULLY"));
		
	}
	@GetMapping("/plant")
	public ResponseEntity<List<Plant>> getAllMachine(){
		 List<Plant> plant = new ArrayList<Plant>();
		 
		 plantService.selctAllPlants().forEach(plant::add);
		 
		 if(plant.isEmpty()) {
			 return new ResponseEntity<List<Plant>>(HttpStatus.NO_CONTENT);
		 }
		 return new ResponseEntity<List<Plant>>(plant,HttpStatus.OK);
 	}
	@GetMapping("/machine/{id}")
	public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
		
		Optional<Plant> plantdata = plantService.selectPlantById(id);
		
		if(plantdata.isPresent()) {
			return new ResponseEntity<Plant>(plantdata.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/machine/{machinename}")
	public ResponseEntity<Plant> getPlantByName(@PathVariable String plantname){
		
	  Plant plantdata = plantService.findByPlantName(plantname);
	  
	  if(plantdata != null) {
		  return new ResponseEntity<Plant>(plantdata,HttpStatus.OK);
	  }
	  else {
		  return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
		  
	  }
	}
	
	@PutMapping("/plant/{id}")
	public ResponseEntity<Plant> getPlantById(@PathVariable Long id,@RequestBody Plant plantdetsils){
		
		Optional<Plant> plantdata = plantService.selectPlantById(id);
		if (plantdata.isPresent()) {
			Plant plant = plantdata.get();
			plant.setPlantName(plantdetsils.getPlantName());
			plant.setLocation(plantdetsils.getLocation());
			return new ResponseEntity<Plant>(plantService.savePlant(plant),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/plant/{id}")
	public ResponseEntity<List<Plant>> deletePlant(@PathVariable Long id){
		
		plantService.deletePlantById(id);
		return new ResponseEntity<List<Plant>>(HttpStatus.OK);
	}
	
	@GetMapping("/plant/{id}/machine")
	public ResponseEntity<List<Plant>> getAllPlantByMachineId(@PathVariable Long id){
		
		if(!machineService.existsById(id)) {
			throw new ResourceNotFoundException("Machine", "id", id);
		}
		List<Plant> plant = plantService.findPlantsByMachinesId(id);
		return new ResponseEntity<List<Plant>>(plant,HttpStatus.OK);
		
	}
	
	@PutMapping("/{plantid}/machine")
	public Plant assignMachineToPlant(@PathVariable Long plantid,@RequestBody List<Long> machineid) {
		
		Set<Machine> machineSet = null;
		Plant plant = plantService.selectPlantById(plantid).get();
		for(int i = 0;i<machineid.size();i++) {
			Machine machine = machineService.selectMachineById(machineid.get(i)).get();
			machineSet = plant.getMachines();
			machineSet.add(machine);
		}
		plant.setMachines(machineSet);
		return plantService.savePlant(plant);
	}
	
	@PostMapping("/{plantid}/machine")
	public ResponseEntity<Plant> addMachineToPlant(@PathVariable Long plantid,@RequestBody List<Long> machineids){
		
		Optional<Plant> plant = plantService.selectPlantById(plantid);
		if(plant.isPresent()) {
			Set<Machine> machines = new HashSet<>();
			for(Long machineId : machineids) {
				Optional<Machine> machine = machineService.selectMachineById(machineId);
				if(machine.isPresent()) {
					machines.add(machine.get());
				}
			}
			plant.get().getMachines().addAll(machines);
			plantService.savePlant(plant.get());
			return ResponseEntity.ok(plant.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{plantid}/machines/{machineid}")
	public ResponseEntity<Plant> removeMachineFromPlant(@PathVariable Long plantid,@PathVariable Long machineId){
		
		Optional<Plant> plant = plantService.selectPlantById(plantid);
		Optional<Machine> machine = machineService.selectMachineById(machineId);
		if(plant.isPresent() && machine.isPresent()) {
			plant.get().getMachines().remove(machine.get());
			plantService.savePlant(plant.get());
			return ResponseEntity.ok(plant.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
