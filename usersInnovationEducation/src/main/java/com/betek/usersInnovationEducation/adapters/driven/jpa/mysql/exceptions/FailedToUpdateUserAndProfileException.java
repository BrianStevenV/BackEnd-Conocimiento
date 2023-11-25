package com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.exceptions;

import jakarta.persistence.PersistenceException;

public class FailedToUpdateUserAndProfileException extends PersistenceException {
    public FailedToUpdateUserAndProfileException() { super(); }
}
