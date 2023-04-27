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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "machine")
public class Machine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_id")
	private Long id ;
	
	@Column(name = "machine_name")
	private String machineName;
	
	@Column(name = "machine_desc")
	private String machineDesc;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "machine_operation",
	joinColumns = {@JoinColumn(name = "machine_id")},
	inverseJoinColumns = {@JoinColumn(name = "operation_id")})
	
	private Set<Operation> operations = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST,CascadeType.MERGE}, 
			mappedBy = "machines")
	@JsonIgnore
	private Set<Plant> plants = new HashSet<>();
	
	public Machine() {
		
	}	

	public Machine(String machineName, String machineDesc) {
		super();
		this.machineName = machineName;
		this.machineDesc = machineDesc;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineDesc() {
		return machineDesc;
	}

	public void setMachineDesc(String machineDesc) {
		this.machineDesc = machineDesc;
	}

	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, machineDesc, machineName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		return id == other.id && Objects.equals(machineDesc, other.machineDesc)
				&& Objects.equals(machineName, other.machineName);
	}

	@Override
	public String toString() {
		return "Machine [machineName=" + machineName + 
				", machineDesc=" + machineDesc + "]";
	}
	
	
	
	
	
}
