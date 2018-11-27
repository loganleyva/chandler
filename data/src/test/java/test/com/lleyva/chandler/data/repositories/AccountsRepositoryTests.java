package test.com.lleyva.chandler.data.repositories;

import com.lleyva.chandler.data.Account;
import com.lleyva.chandler.data.enums.AccountRole;
import com.lleyva.chandler.data.repositories.AccountsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import test.com.lleyva.chandler.data.config.DataTestConfiguration;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataTestConfiguration.class)
public class AccountsRepositoryTests {

	////////////////
	// Attributes //
	////////////////

	////////////////
	// Injections //
	////////////////

	@Autowired
	private AccountsRepository accountsRepository;

	///////////
	// Tests //
	///////////

	@Test
	public void findByEmailTest() {
		// Init Test Objects //
		Account account = generateAccount();
		String email = account.getEmail();

		// Bootstrap Data //
		accountsRepository.save(account);

		// Fetch and Verify //
		Optional<Account> fetchedAccount = accountsRepository.findByEmail(email);

		Assert.assertTrue(fetchedAccount.isPresent());
		Assert.assertTrue(fetchedAccount.get().equals(account));
	}

	/////////////////////
	// Private Methpds //
	/////////////////////

	private Account generateAccount() {
		Account account = new Account();
		account.setEmail("TEST@" + RandomStringUtils.randomAlphanumeric(10) + ".com");
		account.setHashedPassword("FAKE");
		account.setRole(AccountRole.NONE);
		return account;
	}
}
