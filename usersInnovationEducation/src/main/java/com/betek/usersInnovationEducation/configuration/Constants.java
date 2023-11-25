package com.betek.usersInnovationEducation.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_CREATED_MESSAGE = "User created successfully.";
    public static final String PROFILE_CREATED_MESSAGE = "Profile created successfully.";
    public static final String UPDATE_ALL_INFORMATION_MESSAGE = "The information was updated successfully.";
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String ACCOUNT_ENABLED_MESSAGE = "The account was enabled.";
    public static final String ROLE_ADMINSITRATOR = "ADMINISTRATOR_ROLE";
    public static final String ROLE_MEMBER = "MEMBER_ROLE";

    // exceptions
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "An user already exists with the Email provided.";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists.";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials or role not allowed.";

    public static final String FAILED_TO_UPDATE_USER_AND_PROFILE = "Failed to update user and profile. Rolling back transaction.";

    // Open API
    public static final String SWAGGER_TITLE_MESSAGE = "User API RedEAmerica";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
