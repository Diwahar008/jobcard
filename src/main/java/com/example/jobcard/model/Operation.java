package com.example.jobcard.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "operation")
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_id")
	private Long id;
	@Column(name = "operation_name")
	private String operationName;
	@Column(name = "operation_desc")
	private String operationDesc;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			mappedBy = "operations")
	@JsonIgnore
	private Set<Machine> machines = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST,CascadeType.MERGE}, 
			mappedBy = "operations")
	@JsonIgnore
	private Set<Product> products = new HashSet<>();
	
	
	
	
	


	public Operation() {
		
	}

	
	public Operation(String operationName, String operationDesc) {
		super();
		this.operationName = operationName;
		this.operationDesc = operationDesc;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public Set<Machine> getMachines() {
		return machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}
	
	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Operation [operationName=" + operationName + 
				", operationDesc=" + operationDesc + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, operationDesc, operationName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		return Objects.equals(id, other.id) && Objects.equals(operationDesc, other.operationDesc)
				&& Objects.equals(operationName, other.operationName);
	}

	
	
	
	
}
