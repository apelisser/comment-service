package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class CommentModerationClientException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6593650256393835416L;

    private final Integer httpStatusCode;
    private final String message;

    public CommentModerationClientException(Integer httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

}
