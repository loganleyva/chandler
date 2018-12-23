package com.lleyva.chandler.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lleyva.chandler.data.constants.EntityConstants;
import com.lleyva.chandler.data.constants.JsonConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity(name = EntityConstants.PICTURE_TABLE)
public class Picture extends EntityBase {

	////////////////
	// Attributes //
	////////////////

	@JsonProperty(value = JsonConstants.PICTURE_TITLE)
	@Column(name = EntityConstants.PICTURE_TITLE, nullable = true, length = 255)
	private String title;

	@JsonProperty(value = JsonConstants.PICTURE_UPLOADING_USER)
	@Column(name = EntityConstants.PICTURE_UPLOADING_USER, nullable = false)
	private UUID uploadingUser;

	@JsonProperty(value = JsonConstants.PICTURE_USERS_IN)
	@Column(name = EntityConstants.PICTURE_USERS_IN, nullable = true)
	private UUID[] usersInPicture;

	@JsonProperty(value = JsonConstants.PICTURE_LOCATION)
	@Column(name = EntityConstants.PICTURE_IMAGE_LOCATION, nullable = true, length = 255)
	private String imageLocation;

	@JsonProperty(value = JsonConstants.PICTURE_THUMB_LOCATION)
	@Column(name = EntityConstants.PICTURE_THUMB_LOCATION, nullable = true, length = 255)
	private String thumbnailLocation;

	/////////////////
	// Constructor //
	/////////////////

	/**
	 * Constructor
	 */
	public Picture() {
		super();
	}

	/////////////////////////
	// Getters and Setters //
	/////////////////////////

	/**
	 * Gets title
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 *  Sets title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets user that uploaded the Picture
	 */
	public UUID getUploadingUser() {
		return this.uploadingUser;
	}

	/**
	 * Sets the uploading user
	 * @param uploadingUser
	 */
	public void setUploadingUser(UUID uploadingUser) {
		this.uploadingUser = uploadingUser;
	}

	/**
	 * Gets list of users that are present in the Picture
	 * @return
	 */
	public UUID[] getUsersInPicture() {
		return this.usersInPicture;
	}

	/**
	 * Sets rthe users that are present in the Picture
	 * @param usersInPicture
	 */
	public void setUsersInPicture(UUID[] usersInPicture) {
		this.usersInPicture = usersInPicture;
	}

	/**
	 * Gets location of the Picture
	 * @return
	 */
	public String getImageLocation() {
		return this.imageLocation;
	}

	/**
	 * Sets the location of the Picture
	 * @param imageLocation
	 */
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/**
	 * Gets the location of the picture thumbnail
	 * @return
	 */
	public String getThumbnailLocation() {
		return this.thumbnailLocation;
	}

	/**
	 * Sets the location of the picture thumbnail
	 * @param thumbnailLocation
	 */
	public void setThumbnailLocation(String thumbnailLocation) {
		this.thumbnailLocation = thumbnailLocation;
	}

}
