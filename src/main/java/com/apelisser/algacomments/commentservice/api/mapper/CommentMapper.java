package com.apelisser.algacomments.commentservice.api.mapper;

import com.apelisser.algacomments.commentservice.api.model.CommentOutput;
import com.apelisser.algacomments.commentservice.api.model.PageModel;
import com.apelisser.algacomments.commentservice.api.model.input.CommentInput;
import com.apelisser.algacomments.commentservice.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {

    public Comment toEntity(CommentInput commentInput) {
        var entity = new Comment();
        entity.setAuthor(commentInput.getAuthor());
        entity.setText(commentInput.getText());
        return entity;
    }

    public CommentOutput toModel(Comment comment) {
        var model = new CommentOutput();
        model.setId(comment.getId().toString());
        model.setAuthor(comment.getAuthor());
        model.setText(comment.getText());
        model.setCreatedAt(comment.getCreatedAt());
        return model;
    }

    public PageModel<CommentOutput> toPageModel(Page<Comment> page) {
        List<Comment> pageContent = page.getContent();

        List<CommentOutput> pageModel = pageContent.stream()
            .map(this::toModel)
            .toList();

        return PageModel.of(
            pageModel,
            page.getSize(),
            page.getNumber(),
            page.getTotalPages(),
            page.getTotalElements());
    }

}
