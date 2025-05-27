package com.apelisser.algacomments.commentservice.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentOutput {

    private String id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;

}
