package com.lleyva.chandler.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lleyva.chandler.data.constants.EntityConstants;
import com.lleyva.chandler.data.constants.JsonConstants;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity(name = EntityConstants.POST_TABLE)
public class Post extends EntityBase {

	////////////////
	// Attributes //
	////////////////

	@JsonProperty(value = JsonConstants.POST_CREATED_DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "UTC")
	@Column(name = EntityConstants.POST_CREATED_TIME_DATE, nullable = false)
	private Instant createdTimeDate;

	@JsonProperty(value = JsonConstants.POST_AUTHOR)
	@Column(name = EntityConstants.POST_AUTHOR_ID, nullable = false)
	private UUID author;

	@JsonProperty(value = JsonConstants.POST_PICTURE)
	@Column(name = EntityConstants.POST_PICTURE_ID, nullable = true)
	private UUID picture;

	@JsonProperty(value = JsonConstants.POST_MESSAGE)
	@Column(name = EntityConstants.POST_MESSAGE_COLUMN, nullable = true, length = 255)
	private String message;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * Constructor
	 */
	public Post() {
		super();
		createdTimeDate = Instant.now();
	}

	/////////////////////////
	// Getters and Setters //
	/////////////////////////

	/**
	 * Fetches the created time/date
	 * @return
	 */
	public Instant getCreatedTimeDate() {
		return this.createdTimeDate;
	}

	/**
	 * Sets the created time/date
	 * @param createdTimeDate
	 */
	public void setCreatedTimeDate(Instant createdTimeDate) {
		this.createdTimeDate = createdTimeDate;
	}

	/**
	 * Fetches the Author of the Post
	 * @return
	 */
	public UUID getAuthor() {
		return this.author;
	}

	/**
	 * Sets the Author of the Post
	 * @param author
	 */
	public void setAuthor(UUID author) {
		this.author = author;
	}

	/**
	 * Fetches the Picture associated with the Post
	 * @return
	 */
	public UUID getPicture() {
		return this.picture;
	}

	/**
	 * Sets the Picture associated with the Post
	 * @param picture
	 */
	public void setPicture(UUID picture) {
		this.picture = picture;
	}

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
