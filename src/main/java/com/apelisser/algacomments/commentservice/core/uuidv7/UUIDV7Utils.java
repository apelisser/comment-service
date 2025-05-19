package com.apelisser.algacomments.commentservice.core.uuidv7;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public final class UUIDV7Utils {

    private UUIDV7Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static OffsetDateTime extractOffsetDateTime(UUID uuid) {
        if (uuid == null) {
            return null;
        }

        long timestamp = uuid.getMostSignificantBits() >>> 16;
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

}
