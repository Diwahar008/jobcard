package com.example.jobcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobcard.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>{
	
	List<Operation> findOperationsByMachinesId(Long id);
	
	List<Operation> findOperationsByproductsId(Long id);

}
