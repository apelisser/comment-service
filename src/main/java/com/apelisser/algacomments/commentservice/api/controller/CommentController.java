package com.apelisser.algacomments.commentservice.api.controller;

import com.apelisser.algacomments.commentservice.api.mapper.CommentMapper;
import com.apelisser.algacomments.commentservice.api.model.CommentOutput;
import com.apelisser.algacomments.commentservice.api.model.PageModel;
import com.apelisser.algacomments.commentservice.api.model.input.CommentInput;
import com.apelisser.algacomments.commentservice.domain.model.Comment;
import com.apelisser.algacomments.commentservice.domain.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public PageModel<CommentOutput> getAllPaginated(@PageableDefault Pageable pageable) {
        Page<Comment> commentsPaginated = commentService.getAllCommentsPaginated(pageable);
        return commentMapper.toPageModel(commentsPaginated);
    }

    @GetMapping("/{id}")
    public CommentOutput getById(@PathVariable UUID id) {
        Comment comment = commentService.findByIdOrFail(id);
        return commentMapper.toModel(comment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentOutput add(@RequestBody CommentInput commentInput) {
        Comment comment = commentMapper.toEntity(commentInput);
        Comment publishedComment = commentService.moderateAndPublish(comment);
        return commentMapper.toModel(publishedComment);
    }

}
