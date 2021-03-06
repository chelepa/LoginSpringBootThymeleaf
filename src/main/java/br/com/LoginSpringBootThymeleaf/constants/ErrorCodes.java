package br.com.LoginSpringBootThymeleaf.constants;

public enum ErrorCodes {
	INTERNAL_SERVER_ERROR("Internal server error"),
	INVALID_CREDENTIALS("Invalid credentials"),
	INVALID_REQUEST("Invalid request");
	
    private final String message;

    ErrorCodes(String message) {
    	this.message = message;
    }

	public String getMessage() {
    	return message;
	}
}
