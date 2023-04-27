package com.example.jobcard.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobcard.model.Machine;
import com.example.jobcard.repository.MachineRepository;
import com.example.jobcard.service.MachineService;

@Service
public class MachineServiceImpl implements MachineService{

	@Autowired
	private MachineRepository machineRepository;
	
	@Override
	public Machine saveMachine(Machine machine) {
		return machineRepository.save(machine);
	}

	@Override
	public List<Machine> selectAllMachines() {

		return machineRepository.findAll();
	}

	@Override
	public Optional<Machine> selectMachineById(Long id) {
		
		return machineRepository.findById(id);
	}

	/*
	 * @Override public Machine updateMachineById(Machine machine, long id) {
	 * 
	 * return null; }
	 */

	@Override
	public List<Machine> findMachinesByOperationsId(Long id) {

		return machineRepository.findMachinesByOperationsId(id);
	}
	
	@Override
	public Machine findByMachineName(String machinename) {

		return machineRepository.findByMachineName(machinename) ;
	}

	@Override
	public void deleteMachine(Long id) {
		machineRepository.deleteById(id);		
	}

	@Override
	public Boolean existsById(Long id) {
		
		return machineRepository.existsById(id);
	}

	
	
	
}
