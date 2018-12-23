package com.lleyva.chandler.services;

import com.lleyva.chandler.data.Account;
import com.lleyva.chandler.data.enums.AccountRole;
import com.lleyva.chandler.data.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class AccountsService extends CrudServiceBase<Account> implements UserDetailsService {

	////////////////
	// Attributes //
	////////////////

	////////////////
	// Injections //
	////////////////

	@Value("${admin.initial.bootstrap.email:}")
	private String bootstrapAdminEmail;

	@Value("${admin.initial.bootstrap.password:}")
	private String bootstrapAdminPassword;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountsRepository accountsRepository;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * Default void Constructor
	 */
	public AccountsService() { }

	/**
	 * Init method that bootstraps an administrator, if applicable/present
	 */
	@PostConstruct
	public void init() {
		if(!bootstrapAdminEmail.equals("") && !bootstrapAdminPassword.equals("")) {
			LOGGER.info("Admin user to bootstrap detected!");

			// Check to see if Account already exists, then bootstrap if necessary //
			Optional<Account> bootstrapAccount = accountsRepository.findByEmail(bootstrapAdminEmail);
			if(bootstrapAccount.isPresent()) {
				LOGGER.info("Admin user has ALREADY been bootstrapped.");
			} else {
				Account account = new Account(
						bootstrapAdminEmail,
						passwordEncoder.encode(bootstrapAdminPassword),
						AccountRole.ADMIN);
				accountsRepository.save(account);
				LOGGER.info("Admin user SUCCESSFULLY BOOTSTRAPPED!");
			}
		}
	}

	///////////////
	// Overrides //
	///////////////

	/**
	 * Loads user by email
	 * @param email
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(
			String email) throws UsernameNotFoundException {
		Assert.hasText(email, "email is null or empty");

		Optional<Account> fetchedAccount = accountsRepository.findByEmail(email);
		if(fetchedAccount.isPresent()) {
			return fetchedAccount.get();
		} else {
			throw new UsernameNotFoundException("No user found with email: " + email);
		}
	}

	////////////////////
	// Public Methods //
	////////////////////

	public Account getCurrentAccount() {
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return account;
	}

}
