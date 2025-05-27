package com.apelisser.algacomments.commentservice.domain.repository;

import com.apelisser.algacomments.commentservice.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository <Comment, UUID> {

}
