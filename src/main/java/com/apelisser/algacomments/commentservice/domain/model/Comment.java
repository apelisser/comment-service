package com.apelisser.algacomments.commentservice.domain.model;

import com.apelisser.algacomments.commentservice.domain.transfer.ModeratableComment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment implements ModeratableComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
    }

    @Override
    public String getCommentId() {
        return id != null ? id.toString() : null;
    }

    @Override
    public String getText() {
        return text;
    }

}
