package com.scyula.trelloconnector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error with Trello API")
public class TrelloAPIException extends Exception{

    public TrelloAPIException() {}

    public TrelloAPIException(String message) {

        super(message);
    }

    public TrelloAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}

