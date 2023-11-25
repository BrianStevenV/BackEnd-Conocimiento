package com.betek.usersInnovationEducation.configuration;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions.FailedToUpdateUserAndProfileException;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.betek.usersInnovationEducation.configuration.Constants.FAILED_TO_UPDATE_USER_AND_PROFILE;
import static com.betek.usersInnovationEducation.configuration.Constants.MAIL_ALREADY_EXISTS_MESSAGE;
import static com.betek.usersInnovationEducation.configuration.Constants.RESPONSE_ERROR_MESSAGE_KEY;
import static com.betek.usersInnovationEducation.configuration.Constants.USER_ALREADY_EXISTS_MESSAGE;
import static com.betek.usersInnovationEducation.configuration.Constants.WRONG_CREDENTIALS_MESSAGE;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handlePersonAlreadyExistsException(
            UserAlreadyExistsException personAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMailAlreadyExistsException(
            MailAlreadyExistsException mailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, MAIL_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, WRONG_CREDENTIALS_MESSAGE));
    }

    @ExceptionHandler(FailedToUpdateUserAndProfileException.class)
    public ResponseEntity<Map<String, String>> handleFailedToUpdateUserAndProfileException(
            FailedToUpdateUserAndProfileException failedToUpdateUserAndProfileException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, FAILED_TO_UPDATE_USER_AND_PROFILE));
    }
}
