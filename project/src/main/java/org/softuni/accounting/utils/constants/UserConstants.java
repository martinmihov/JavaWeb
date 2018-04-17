package org.softuni.accounting.utils.constants;

import org.springframework.stereotype.Component;

@Component
public final class UserConstants {

    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 30;
    public static final int PASSWORD_AND_CONFIRM_PASSWORD_MIN_LENGTH = 3;
    public static final int PASSWORD_AND_CONFIRM_PASSWORD_MAX_LENGTH = 64;

    public static final String EMAIL_EMPTY_MESSAGE = "* Email cannot be empty";
    public static final String PASSWORD_EMPTY_MESSAGE = "* Password cannot be empty";
    public static final String PASSWORD_SIZE_MESSAGE = "* Password must be between 3 and 64 symbols in length";
    public static final String PASSWORD_CONFIRM_EMPTY_MESSAGE = "* Confirm Password cannot be empty";
    public static final String PASSWORD_CONFIRM_SIZE_MESSAGE = "* Confirm Password must be between 3 and 64 symbols in length";
    public static final String USERNAME_EMPTY_MESSAGE = "* Username cannot be empty";
    public static final String USERNAME_SIZE_MESSAGE = "* Username must be between 2 and 30 symbols in length";

}
