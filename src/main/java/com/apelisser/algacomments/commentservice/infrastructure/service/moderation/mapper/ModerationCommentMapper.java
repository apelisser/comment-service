package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.mapper;

import com.apelisser.algacomments.commentservice.domain.transfer.ModeratableComment;
import com.apelisser.algacomments.commentservice.domain.transfer.ModerationResult;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model.ModerationCommentRequest;
import com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model.ModerationCommentResponse;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.TRUE;

@Component
public class ModerationCommentMapper {

    public ModerationCommentRequest toModerationCommentRequest(ModeratableComment moderatableComment) {
        var request = new ModerationCommentRequest();
        request.setCommentId(moderatableComment.getCommentId());
        request.setText(moderatableComment.getText());
        return request;
    }

    public ModerationCommentResponse toModerationCommentResponse() {
        return null;
    }

    public ModerationResult toModerationResult(ModerationCommentResponse response) {
        return new ModerationResult() {
            @Override
            public boolean isApproved() {
                return TRUE.equals(response.getApproved());
            }

            @Override
            public String getReason() {
                return response.getReason();
            }
        };
    }

}
