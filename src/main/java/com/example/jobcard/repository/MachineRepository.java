package com.example.jobcard.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobcard.model.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long>{

	List<Machine> findMachinesByOperationsId(Long id);
	
	Machine findByMachineName(String machinename);
	
	
}
