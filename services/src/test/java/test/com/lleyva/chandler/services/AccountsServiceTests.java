package test.com.lleyva.chandler.services;

import com.lleyva.chandler.data.Account;
import com.lleyva.chandler.data.repositories.AccountsRepository;
import com.lleyva.chandler.data.repositories.RepositoryBase;
import com.lleyva.chandler.services.AccountsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AccountsServiceTests {

	////////////////
	// Injections //
	////////////////

	@InjectMocks
	private AccountsService testAccountsService;

	@Mock
	private RepositoryBase<Account> mockAccountsRepositoryBase;

	@Mock
	private AccountsRepository mockAccountsRepository;

	@Mock
	private PasswordEncoder mockPasswordEncoder;

	///////////
	// Setup //
	///////////

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	///////////
	// Tests //
	///////////

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsername_NoUserFoundTest() {
		// Init Test Objects //
		String testEmail = RandomStringUtils.randomAlphanumeric(16);

		// Configure Mocks //
		Optional<Account> optionalAccount = Optional.empty();
		Mockito.doReturn(optionalAccount).when(mockAccountsRepository)
				.findByEmail(testEmail);

		// Call and Verify //
		testAccountsService.loadUserByUsername(testEmail);
	}

	@Test
	public void loadUserByUsername_UserFoundTest() {
		// Init Test Objects //
		String testEmail = RandomStringUtils.randomAlphanumeric(16);
		Account account = new Account();
		account.setEmail(testEmail);

		// Configure Mocks //
		Optional<Account> optionalAccount = Optional.of(account);
		Mockito.doReturn(optionalAccount).when(mockAccountsRepository)
				.findByEmail(testEmail);

		// Call and Verify //
		UserDetails loadedUser = testAccountsService.loadUserByUsername(testEmail);

		Assert.assertTrue(loadedUser instanceof Account);
		Assert.assertTrue(account.equals((Account)loadedUser));
	}

}