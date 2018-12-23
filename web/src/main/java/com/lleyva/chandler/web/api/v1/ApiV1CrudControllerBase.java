package com.lleyva.chandler.web.api.v1;

import com.lleyva.chandler.data.EntityBase;
import com.lleyva.chandler.services.CrudServiceBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

public abstract class ApiV1CrudControllerBase<Entity extends EntityBase> {

	////////////////
	// Attributes //
	////////////////

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	////////////////
	// Injections //
	////////////////

	@Autowired
	private CrudServiceBase<Entity> service;

	///////////////
	// Endpoints //
	///////////////

	/**
	 * Creates(/Persists) passed in Entity
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Entity create(
			@RequestBody Entity entity) {
		Assert.notNull(entity, "entity is null");
		LOGGER.info("POST called");

		return service.create(entity);
	}

	/**
	 * Reads all Entites
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Entity> readAll() {
		LOGGER.info("GET called for all Entities");

		return service.readAll();
	}

	/**
	 * Reads Entity by ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Entity read(
			@PathVariable(value = "id") UUID id) {
		Assert.notNull(id, "id is null");
		LOGGER.info("GET called on id: "+ id);

		return service.read(id);
	}

	/**
	 * Replaces Entity by ID
	 * @param id
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Entity update(
			@PathVariable(value = "id") UUID id,
			@RequestBody Entity entity) {
		Assert.notNull(id, "id is null");
		Assert.notNull(entity, "entity is null");
		LOGGER.info("PUT called on id: " + id);

		return service.update(id, entity);
	}

	/**
	 * Deletes Entity by ID
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(
			@PathVariable(value = "id") UUID id) {
		Assert.notNull(id, "id is null");
		LOGGER.info("DELETED called on id: " + id);

		service.delete(id);
	}

}
