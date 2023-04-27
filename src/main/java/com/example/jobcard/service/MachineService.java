package com.example.jobcard.service;

import java.util.List;
import java.util.Optional;
import com.example.jobcard.model.Machine;

public interface MachineService {
	
	Machine saveMachine(Machine machine);
	

	List<Machine> selectAllMachines();
	

	Optional<Machine> selectMachineById(Long id);
	

	//Machine updateMachineById(Machine machine,long id);

	
	List<Machine> findMachinesByOperationsId(Long id);
	
	
	Machine findByMachineName (String machinename);

	
	void deleteMachine(Long id);
	

	Boolean existsById(Long id);

	

}
