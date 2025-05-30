package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.factory;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
public class ModerationRestClientFactory {

    private final RestClient.Builder builder;
    private final ResponseErrorHandler errorHandler;

    public ModerationRestClientFactory(RestClient.Builder builder, ExceptionHandler errorHandler) {
        this.builder = builder;
        this.errorHandler = errorHandler;
    }

    public RestClient commentModerationRestClient() {
        return builder.baseUrl("http://localhost:8081")
            .requestFactory(generateClientHttpRequestFactory())
            .defaultStatusHandler(errorHandler)
            .build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setReadTimeout(Duration.ofSeconds(5));
        factory.setConnectTimeout(Duration.ofSeconds(3));

        return factory;
    }

}
