package com.example.jobcard.service;

import java.util.List;
import java.util.Optional;
import com.example.jobcard.model.Operation;

public interface OperationService {


		Operation saveOperation(Operation operation);


		List<Operation> selectAllOperations(); 
		

		Optional<Operation> selectOperationById(Long id);
		

	//	Operation updateOperation(Operation operation,long id);
		
		
		List<Operation> findOperationsByMachinesId(Long id);
		
		
	//	List<Operation> findOperationsByproductsId(Long id);
		
		
		void  deleteOperationById(long id);
		

		Boolean existsById(Long id);

}
