package com.pcs.tradingapp.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity representing a user role in the application. 
 * Implements {@link Serializable} interface to prevent serialization issues on authentication. 
 * <p>
 * Each user has a single role that specifies their access rights. 
 * Role names are defined in {@link RoleName}.   
 * </p>
 */
@Entity
@Table(name="role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	RoleName name;
	
	public Role() {}

	/**
	 * Constructs a role with the specified name
	 * @param name the {@link RoleName}
	 */
	public Role(RoleName name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public RoleName getName() {
		return name;
	}
}
