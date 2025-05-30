package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.factory;

import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.exception.CommentModerationClientException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
public class ExceptionHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        throw new CommentModerationClientException(response.getStatusCode().value(), response.getStatusText());
    }

}
