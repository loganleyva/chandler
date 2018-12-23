package com.lleyva.chandler.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface RepositoryBase<T> extends CrudRepository<T, Long> {

	Optional<T> findByExposedId(UUID exposedId);

	void deleteByExposedId(UUID exposedId);

}
