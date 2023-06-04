package org.acme.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderInputException extends RuntimeException {

    public OrderInputException(String msg) {
        super(msg);
    }
}
