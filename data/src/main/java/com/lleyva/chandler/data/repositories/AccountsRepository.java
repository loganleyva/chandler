package com.lleyva.chandler.data.repositories;

import com.lleyva.chandler.data.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends RepositoryBase<Account> {

	Optional<Account> findByEmail(String email);

}
