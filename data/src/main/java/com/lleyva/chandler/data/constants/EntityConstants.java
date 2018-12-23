package com.lleyva.chandler.data.constants;

public class EntityConstants {

	//////////////////////////
	// EntityBase Constants //
	//////////////////////////

	public static final String EXPOSED_ID = "exposedid";

	///////////////////////
	// Account Constants //
	///////////////////////

	public static final String ACCOUNT_TABLE = "accounts";

	public static final String ACCOUNT_EMAIL = "email";
	public static final String ACCOUNT_HASHED_PASSWORD = "password";
	public static final String ACCOUNT_ROLE = "role";
	public static final String ACCOUNT_FIRST_NAME = "firstname";
	public static final String ACCOUNT_LAST_NAME = "lastname";
	public static final String ACCOUNT_STATE = "state";

	///////////////////////
	// Picture Constants //
	///////////////////////

	public static final String PICTURE_TABLE = "pictures";

	public static final String PICTURE_TITLE = "title";
	public static final String PICTURE_UPLOADING_USER = "uploadinguser";
	public static final String PICTURE_USERS_IN = "usersin";
	public static final String PICTURE_IMAGE_LOCATION = "location";
	public static final String PICTURE_THUMB_LOCATION = "thumblocation";

	////////////////////
	// Post Constants //
	////////////////////

	public static final String POST_TABLE = "posts";

	public static final String POST_CREATED_TIME_DATE = "createdtimedate";
	public static final String POST_AUTHOR_ID = "authorid";
	public static final String POST_PICTURE_ID = "pictureid";
	public static final String POST_MESSAGE_COLUMN = "message";

}
