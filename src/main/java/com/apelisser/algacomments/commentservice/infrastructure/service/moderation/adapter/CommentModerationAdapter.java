package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.adapter;

import com.apelisser.algacomments.commentservice.domain.service.ModerationService;
import com.apelisser.algacomments.commentservice.domain.transfer.ModeratableComment;
import com.apelisser.algacomments.commentservice.domain.transfer.ModerationResult;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.client.ModerationClient;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.mapper.ModerationCommentMapper;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model.ModerationCommentRequest;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model.ModerationCommentResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentModerationAdapter implements ModerationService {

    private final ModerationClient moderationClient;
    private final ModerationCommentMapper moderationMapper;

    public CommentModerationAdapter(ModerationClient moderationClient, ModerationCommentMapper moderationMapper) {
        this.moderationClient = moderationClient;
        this.moderationMapper = moderationMapper;
    }

    @Override
    public ModerationResult applyModeration(ModeratableComment comment) {
        ModerationCommentRequest commentRequest = moderationMapper.toModerationCommentRequest(comment);
        ModerationCommentResponse commentResponse = moderationClient.moderate(commentRequest);
        return moderationMapper.toModerationResult(commentResponse);
    }

}
