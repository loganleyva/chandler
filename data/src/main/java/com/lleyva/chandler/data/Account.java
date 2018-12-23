package com.lleyva.chandler.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lleyva.chandler.data.constants.EntityConstants;
import com.lleyva.chandler.data.constants.JsonConstants;
import com.lleyva.chandler.data.enums.AccountRole;
import com.lleyva.chandler.data.enums.AccountState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Account extends EntityBase implements UserDetails {

	////////////////
	// Attributes //
	////////////////

	public static final int MAX_EMAIL_LENGTH = 100;
	public static final int MAX_HASHED_PASSWORD_LENGTH = 100;

	@JsonProperty(value = JsonConstants.ACCOUNT_EMAIL)
	@Column(name = EntityConstants.ACCOUNT_EMAIL, nullable = false, length = MAX_EMAIL_LENGTH, unique = true)
	private String email;

	@JsonIgnore
	@Column(name = EntityConstants.ACCOUNT_HASHED_PASSWORD, nullable = false, length = MAX_HASHED_PASSWORD_LENGTH)
	private String hashedPassword;

	@JsonIgnore
	@Column(name = EntityConstants.ACCOUNT_ROLE, nullable = false)
	private AccountRole role;

	@JsonProperty(value = JsonConstants.ACCOUNT_FIRST_NAME)
	@Column(name = EntityConstants.ACCOUNT_FIRST_NAME, nullable = true, length = 25)
	private String firstName;

	@JsonProperty(value = JsonConstants.ACCOUNT_LAST_NAME)
	@Column(name = EntityConstants.ACCOUNT_LAST_NAME, nullable = true, length = 25)
	private String lastName;

	@JsonIgnore
	@Column(name = EntityConstants.ACCOUNT_STATE, nullable = false)
	private AccountState state;

	//////////////////
	// Constructors //
	//////////////////

	/**
	 * Default void Constructor
	 */
	public Account() {
		super();
		role = AccountRole.NONE;
		state = AccountState.ACTIVE;
	}

	/**
	 * Constructor
	 * @param email
	 * @param hashedPassword
	 * @param role
	 */
	public Account(
			String email,
			String hashedPassword,
			AccountRole role) {
		super();
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.role = role;
		this.state =AccountState.ACTIVE;
	}

	/**
	 * Constructor
	 * @param email
	 * @param hashedPassword
	 * @param accountRole
	 * @param firstName
	 * @param lastName
	 */
	public Account(
			String email,
			String hashedPassword,
			AccountRole accountRole,
			String firstName,
			String lastName) {
		super();
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.role = accountRole;
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = AccountState.ACTIVE;
	}

	/////////////////////////
	// Getters and Setters //
	/////////////////////////

	/**
	 * Fetches Account email
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets Account email
	 * @param email
	 */
	public void setEmail(String email) {
		Assert.hasText(email, "email is null or empty");
		Assert.isTrue(email.length() <= MAX_EMAIL_LENGTH, "email is longer than " + MAX_EMAIL_LENGTH + " character limit!");
		this.email = email;
	}

	/**
	 * Fetches Account hashed password
	 * @return
	 */
	public String getHashedPassword() {
		return this.hashedPassword;
	}

	/**
	 * Sets Account hashed password
	 * @param hashedPassword
	 */
	public void setHashedPassword(String hashedPassword) {
		Assert.hasText(hashedPassword, "hashedPassword is null or empty");
		Assert.isTrue(hashedPassword.length() <= MAX_HASHED_PASSWORD_LENGTH, "hashedPassword is longer than " + MAX_HASHED_PASSWORD_LENGTH + " character limit!");
		this.hashedPassword = hashedPassword;
	}

	/**
	 * Gets Account role
	 * @return
	 */
	public AccountRole getRole() {
		return this.role;
	}

	/**
	 * Sets Account role
	 * @param role
	 */
	public void setRole(AccountRole role) {
		this.role = role;
	}

	/**
	 * Gets first name
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets last name
	 * @return
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the state
	 * @return
	 */
	public AccountState getState() {
		return this.state;
	}

	/**
	 * Sets the state
	 * @param state
	 */
	public void setState(AccountState state) {
		this.state = state;
	}

	////////////////////
	// Public Methods //
	////////////////////

	/**
	 * Gets Accounts authorities
	 * @return
	 */
	@JsonIgnore
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
		return authorities;
	}

	/**
	 * Gets if the Account is not expired
	 * @return
	 */
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Gets if the Account is not locked
	 * @return
	 */
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return (state != AccountState.LOCKED);
	}

	/**
	 * Gets if the Account credentials are expired
	 * @return
	 */
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return (state != AccountState.EXPIRED_CREDENTIALS);
	}

	/**
	 * Gets if the Account is enabled
	 * @return
	 */
	@JsonIgnore
	public boolean isEnabled() {
		return (state == AccountState.ACTIVE);
	}

	/**
	 * Gets the Account username
	 * @return
	 */
	@JsonIgnore
	public String getUsername() {
		return this.email;
	}

	/**
	 * Gets the Account password (not implemented)
	 * @return
	 */
	@JsonIgnore
	public String getPassword() {
		return this.getHashedPassword();
	}

}
