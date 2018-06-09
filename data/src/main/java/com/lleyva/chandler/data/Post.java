package com.lleyva.chandler.data;

import com.lleyva.chandler.data.constants.EntityConstants;

import javax.persistence.*;

@Entity(name = EntityConstants.POST_NAME)
public class Post extends EntityBase {

	////////////////
	// Attributes //
	////////////////

	@Column(name = EntityConstants.POST_MESSAGE_COLUMN, nullable = true, length = 255)
	private String message;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * Constructor
	 */
	public Post() { }

	/////////////////////////
	// Getters and Setters //
	/////////////////////////

	/**
	 * Fetches Post's Message
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Sets the Post's Message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
