package com.apelisser.algacomments.commentservice.domain.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class CommentNotAllowedException extends DomainException {

    @Serial
    private static final long serialVersionUID = 5152050083086756746L;

    private final String reason;

    public CommentNotAllowedException(String reason) {
        super(reason);
        this.reason = reason;
        super.addProperty("reason", reason);
    }

}
