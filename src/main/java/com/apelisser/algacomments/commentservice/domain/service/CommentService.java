package com.apelisser.algacomments.commentservice.domain.service;

import com.apelisser.algacomments.commentservice.domain.exception.CommentNotAllowedException;
import com.apelisser.algacomments.commentservice.domain.exception.EntityNotFoundException;
import com.apelisser.algacomments.commentservice.domain.model.Comment;
import com.apelisser.algacomments.commentservice.domain.repository.CommentRepository;
import com.apelisser.algacomments.commentservice.domain.transfer.ModerationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModerationService moderationService;

    public CommentService(CommentRepository commentRepository, ModerationService moderationService) {
        this.commentRepository = commentRepository;
        this.moderationService = moderationService;
    }

    public Comment publish(Comment comment) {
        this.applyCommentModeration(comment);
        return commentRepository.save(comment);
    }

    public Comment findByIdOrFail(UUID commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException(Comment.class);
            ex.addProperty("id", commentId.toString());
            return ex;
        });
    }

    public Page<Comment> getAllCommentsPaginated(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    private void applyCommentModeration(Comment comment) {
        ModerationResult moderationResult = moderationService.applyModeration(comment);
        if (!moderationResult.isApproved()) {
            throw new CommentNotAllowedException(moderationResult.getReason());
        }
    }

}
