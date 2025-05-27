package com.apelisser.algacomments.commentservice.infrastructure.service.moderation.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ModerationCommentResponse {

    private Boolean approved;
    private String reason;

}
