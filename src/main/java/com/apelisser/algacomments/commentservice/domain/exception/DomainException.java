package com.apelisser.algacomments.commentservice.domain.exception;

import java.io.Serial;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class DomainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3900131814955562167L;

    private final Map<String, String> properties = new LinkedHashMap<>();

    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    protected DomainException(Throwable cause) {
        super(cause);
    }

    protected DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void addProperty(String name, String value) {
        if (name == null) {
            return;
        }
        properties.put(name, value);
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public String getFormattedProperties() {
        if (properties.isEmpty()) {
            return "[]";
        }

        return properties.entrySet().stream()
            .map(entry -> String.format("(%s, %s)",entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(", ", "[", "]"));
    }

}
