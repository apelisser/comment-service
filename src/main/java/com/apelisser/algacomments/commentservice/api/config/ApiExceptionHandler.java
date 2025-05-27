package com.apelisser.algacomments.commentservice.api.config;

import com.apelisser.algacomments.commentservice.domain.exception.CommentNotAllowedException;
import com.apelisser.algacomments.commentservice.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommentNotAllowedException.class)
    public ProblemDetail handleCommentNotAllowedException(CommentNotAllowedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Comment rejected");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setType(URI.create("/error/comment-rejected"));
        return problemDetail;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource not found");
        String detailMessage = String.format("%s resource not found for parameters: %s",
            ex.getEntityName(),
            ex.getFormattedProperties());

        problemDetail.setDetail(detailMessage);
        problemDetail.setType(URI.create("/error/resource-not-found"));
        return problemDetail;
    }

}
