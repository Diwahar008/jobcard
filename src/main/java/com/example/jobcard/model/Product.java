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

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long id;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_desc")
	private String productdesc;
	

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "product_operation",
	joinColumns = {@JoinColumn(name="product_id")},
			inverseJoinColumns = {@JoinColumn(name = "operation_id")})
	private Set<Operation> operations = new HashSet<>();

	public Product() {
		
	}
	
	
	public Product(String productName, String productdesc) {
		super();
		this.productName = productName;
		this.productdesc = productdesc;
		
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductdesc() {
		return productdesc;
	}

	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}

	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, operations, productName, productdesc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id && Objects.equals(operations, other.operations)
				&& Objects.equals(productName, other.productName) && Objects.equals(productdesc, other.productdesc);
	}
	
	

}
