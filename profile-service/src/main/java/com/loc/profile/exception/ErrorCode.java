package com.loc.profile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized error.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(8888, "Invalid message key.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1101, "User does not exist.", HttpStatus.NOT_FOUND),
    USER_EXISTS(1102, "User already exists.", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1103, "Username must have at least {min} characters.", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1104, "Password must have at least {min} characters.", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1105, "Unathenticated.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1107, "You shall not proceed since You do not have access.", HttpStatus.FORBIDDEN),
    INVALID_DOB(1108, "You must be at least {min} years old to have an account.", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
    }

    private int code;
    private HttpStatusCode statusCode;
    private String message;
}
