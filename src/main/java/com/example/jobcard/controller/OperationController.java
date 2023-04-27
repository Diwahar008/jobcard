package com.example.jobcard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.example.jobcard.model.Operation;
import com.example.jobcard.service.OperationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/operation")
public class OperationController {
	
	@Autowired
	 private OperationService operationService;
	 
	@PostMapping("/machine")
	public ResponseEntity<Operation> createOperation(@RequestBody Operation operation){
		
		return ResponseEntity.ok(operationService.saveOperation(operation));
	}
	
	@GetMapping("/operation")
	public ResponseEntity<List<Operation>> getAllOperation(){
		
		List<Operation> operation = new ArrayList<Operation>();
		
		operationService.selectAllOperations().forEach(operation::add);
		
		if(operation.isEmpty()) {
			return new ResponseEntity<List<Operation>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Operation>>(operation,HttpStatus.OK);
	}
	
	@GetMapping("/operation/{id}")
	public ResponseEntity<Operation> getOperationById(@PathVariable Long id){
	
		Optional<Operation> operationById = operationService.selectOperationById(id);
		if(operationById.isPresent()) {
			return new ResponseEntity<Operation>(operationById.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Operation>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("operation/{id}")
	public ResponseEntity<Operation> getOperationById(@PathVariable Long id,@RequestBody Operation operationdetails){
		
		Optional<Operation> operationById = operationService.selectOperationById(id);
		
		if(operationById.isPresent()) {
			
			Operation operations = operationById.get();
			operations.setOperationName(operationdetails.getOperationName());
			operations.setOperationDesc(operationdetails.getOperationDesc());
			return new ResponseEntity<Operation>(operationService.saveOperation(operations),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Operation>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/operation/{id}")
	public ResponseEntity<?> deleteOperation(@PathVariable Long id){
		operationService.deleteOperationById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
