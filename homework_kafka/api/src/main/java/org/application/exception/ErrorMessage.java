package org.application.exception;


public record ErrorMessage() {
    public static final String USER_NOT_FOUND = "User [%s] not found";
    public static final String EXIST_USER = "There is already an account registered with the same email";
    public static final String EMAIL_ADDRESS_NOT_VALID = "Email address not valid";
    public static final String PHONE_NUMBER_NOT_VALID = "Phone number not valid";
    public static final String INVALID_AGE = "Date of birth cannot be later than the current date";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String NAME_DOES_NOT_EXIST = "First name and last name are required";
}
