package com.example.jobcard.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobcard.model.Operation;
import com.example.jobcard.repository.OperationRepository;
import com.example.jobcard.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Operation saveOperation(Operation operation) {

		return operationRepository.save(operation);
	}

	@Override
	public List<Operation> selectAllOperations() {

		return operationRepository.findAll();
	}

	@Override
	public Optional<Operation> selectOperationById(Long id) {

		return operationRepository.findById(id);
	}

	/*
	 * @Override public Operation updateOperation(Operation operation, long id) {
	 * 
	 * return null; }
	 */

	@Override
	public List<Operation> findOperationsByMachinesId(Long id) {
		return operationRepository.findOperationsByMachinesId(id);
	}

//	@Override
//	public List<Operation> findOperationsByproductsId(Long id) {
//
//		return operationRepository.findOperationsByproductsId(id);
//	}
//	
	@Override
	public void deleteOperationById(long id) {
		operationRepository.deleteById(id);;
		
	}

	@Override
	public Boolean existsById(Long id) {
		return operationRepository.existsById(id);
	}

	
	
}
