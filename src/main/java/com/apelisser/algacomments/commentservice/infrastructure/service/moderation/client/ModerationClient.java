package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.client;

import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.factory.ModerationRestClientFactory;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model.ModerationCommentRequest;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model.ModerationCommentResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ModerationClient {

    private final RestClient restClient;

    public ModerationClient(ModerationRestClientFactory moderationRestClientFactory) {
        this.restClient = moderationRestClientFactory.commentModerationRestClient();
    }

    public ModerationCommentResponse moderate(ModerationCommentRequest commentRequest) {
        return restClient.post()
            .uri("/api/moderate")
            .body(commentRequest)
            .retrieve()
            .body(ModerationCommentResponse.class);
    }
}
