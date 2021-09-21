package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainFeedResponse {

    private int currentPage;

    private int size;

    private int totalPages;

    private int currentPageElements;

    private long totalElements;

    private boolean hasPreviousPage;

    private boolean isFirstPage;

    private boolean hasNextPage;

    private boolean isLastPage;

    private List<ProjectListElementDto> projectList;

}
