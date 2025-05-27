package com.apelisser.algacomments.commentservice.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class PageModel<T> {

    private final List<T> content;
    private final int pageSize;
    private final int currentPage;
    private final int totalPages;
    private final long totalElements;
    private final boolean hasNextPage;

    public PageModel(List<T> content, int pageSize, int currentPage, int totalPages, long totalElements) {
        this.content = content == null
            ? Collections.emptyList()
            : Collections.unmodifiableList(content);
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.hasNextPage = currentPage + 1 < totalPages;
    }

    public static <T> PageModel<T> of(List<T> content, int pageSize, int currentPage, long totalElements) {
        int calculatedTotalPages = pageSize > 0
            ? (int) Math.ceil((double) totalElements / pageSize)
            : 0;

        return new PageModel<>(
            content,
            pageSize,
            currentPage,
            calculatedTotalPages,
            totalElements);
    }

    public static <T> PageModel<T> of(List<T> content, int pageSize, int currentPage, int totalPages,
            long totalElements) {
        return new PageModel<>(
            content,
            pageSize,
            currentPage,
            totalPages,
            totalElements);
    }

}
