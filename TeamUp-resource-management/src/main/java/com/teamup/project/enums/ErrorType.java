package com.teamup.project.enums;

public enum ErrorType {
	INVALID_USER(400, "Invalid user error"), 
	ILLEGAL_USER_INPUT(400, "Illegal user input"),
	USER_ERROR(400, "User error"),
	WORNG_INPUT(400, "Wrong input"), 
	EMAIL_ALREADY_EXISTS(400, "The email you inserted is already in use"),
	TEAM_IS_PRIVATE(400, "An application to a private team must be accepted by team leader"),
	EMPTY_TEAM(400, "Empty group"),
	DB_ERROR(500, "Database error"), 
	DATA_NOT_FOUND(500, "Data not found"), 
	CANNOT_PARSE_DATE(400,"Cannot parse date"),
	ITEM_NOT_REMOVED_PROPERLY(400, "Failed to remove item"),
	HACKING_ATTEMPT(800, "Hacking attempt detected");

	private final int internalErrorCode;
	private final String internalMessage;


	//private Constructor 

	private ErrorType(int internalErrorCode, String internalMessage){
		this.internalErrorCode = internalErrorCode;
		this.internalMessage = internalMessage;

	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getInternalMessage() {
		return internalMessage;
	}

}
