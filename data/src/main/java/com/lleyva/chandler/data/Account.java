package com.lleyva.chandler.data;

import com.lleyva.chandler.data.constants.EntityConstants;
import com.lleyva.chandler.data.enums.AccountRole;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Account extends EntityBase {

	////////////////
	// Attributes //
	////////////////

	public static final int MAX_EMAIL_LENGTH = 100;
	public static final int MAX_HASHED_PASSWORD_LENGTH = 100;

	@Column(name = EntityConstants.USER_EMAIL, nullable = false, length = MAX_EMAIL_LENGTH, unique = true)
	private String email;

	@Column(name = EntityConstants.USER_HASHED_PASSWORD, nullable = false, length = MAX_HASHED_PASSWORD_LENGTH)
	private String hashedPassword;

	@Column(name = EntityConstants.ACCOUNT_ROLE, nullable = false)
	private AccountRole role;

	//////////////////
	// Constructors //
	//////////////////

	/**
	 * Default void Constructor
	 */
	public Account() {
		role = AccountRole.NONE;
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
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.role = role;
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

}
