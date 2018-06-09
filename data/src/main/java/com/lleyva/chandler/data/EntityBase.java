package com.lleyva.chandler.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBase {

	////////////////
	// Attributes //
	////////////////

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * Base Constructor
	 */
	public EntityBase() { }

	/////////////////////////
	// Getters and Setters //
	/////////////////////////

	/**
	 * Fetch Entity ID
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets Entity Id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
