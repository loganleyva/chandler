package com.lleyva.chandler.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lleyva.chandler.data.constants.EntityConstants;
import com.lleyva.chandler.data.constants.JsonConstants;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class EntityBase {

	////////////////
	// Attributes //
	////////////////

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonProperty(value = JsonConstants.id)
	@Column(name = EntityConstants.EXPOSED_ID, nullable = false, unique = true)
	private UUID exposedId;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * Base Constructor
	 */
	protected EntityBase() {
		exposedId = UUID.randomUUID();
	}

	/////////////////////////
	// Getters and Setters //
	/////////////////////////

	/**
	 * Fetch Entity Database ID
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets Entity Database ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Fetches the (exposed) ID of the Entity
	 * @return
	 */
	public UUID getExposedId() {
		return this.exposedId;
	}

	/**
	 * Sets the (exposed) ID of the Entity
	 * @param exposedId
	 */
	public void setExposedId(UUID exposedId) {
		this.exposedId = exposedId;
	}

}
