package ru.javawebinar.topjava.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Restrictor on 11.08.2016.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorrect input")  // 422
public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
