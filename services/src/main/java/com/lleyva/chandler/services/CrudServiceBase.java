package com.lleyva.chandler.services;

import com.lleyva.chandler.data.EntityBase;
import com.lleyva.chandler.data.repositories.RepositoryBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

public abstract class CrudServiceBase <Entity extends EntityBase> {

	////////////////
	// Attributes //
	////////////////

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	////////////////
	// Injections //
	////////////////

	@Autowired
	protected RepositoryBase<Entity> repository;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * void Constructor
	 */
	public CrudServiceBase() { }

	////////////////////
	// Public Methods //
	////////////////////

	/**
	 * Persist(/Create) passed in Entity
	 * @param entity
	 * @return
	 */
	public Entity create(
			Entity entity) {
		Assert.notNull(entity, "entity is null");
		LOGGER.info("CREATE called on " + entity.getClass().getSimpleName());

		// Persist and Return //
		entity.setId(null);
		entity.setExposedId(UUID.randomUUID());
		return repository.save(entity);
	}

	/**
	 * Read all Entities
	 * @return
	 */
	public Iterable<Entity> readAll() {
		LOGGER.info("READ called for all entities");
		return repository.findAll();
	}

	/**
	 * Read Entity with given ID
	 * @param id
	 * @return
	 */
	public Entity read(
			UUID id) {
		Assert.notNull(id, "id is null");
		LOGGER.info("READ called for exposedId: " + id);

		// Find and Return //
		Optional<Entity> entity = repository.findByExposedId(id);
		if(entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException("Entity not found with id: " + id);
		}
	}

	/**
	 * Updates Entity with passed in ID to the given Entity
	 * @param id
	 * @param entity
	 * @return
	 */
	public Entity update(
			UUID id,
			Entity entity) {
		Assert.notNull(id, "id is null");
		Assert.notNull(entity, "entity is null");
		LOGGER.info("UPDATE called on " + entity.getClass().getSimpleName() + " with id: " + id);

		// Fetch existing Entity with given ID //
		Entity persistedEntity;
		Optional<Entity> fetchededEntity = repository.findByExposedId(id);
		if(fetchededEntity.isPresent() == false) {
			throw new EntityNotFoundException("Entity not found with id: " + id);
		} else {
			persistedEntity = fetchededEntity.get();
		}

		// Update the existing Entity //
		BeanUtils.copyProperties(entity, persistedEntity);
		persistedEntity.setExposedId(id);

		return repository.save(persistedEntity);
	}

	/**
	 * Deletes Entity with given ID
	 * @param id
	 */
	public void delete(
			UUID id) {
		Assert.notNull(id, "id is null");
		LOGGER.info("DELETE call for id: " + id);

		// Delete the Entity //
		repository.deleteByExposedId(id);
	}

}
