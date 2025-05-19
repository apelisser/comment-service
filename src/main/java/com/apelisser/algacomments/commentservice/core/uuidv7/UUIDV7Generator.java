package com.apelisser.algacomments.commentservice.core.uuidv7;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDV7Generator {

    private static final TimeBasedEpochRandomGenerator UUID_GENERATOR = Generators.timeBasedEpochRandomGenerator();

    public UUID generateTimeBasedUUID() {
        return UUID_GENERATOR.generate();
    }

}
