package com.apelisser.algacomments.commentservice.api.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentInput {

    private String author;
    private String text;

}
