package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainFeedResponse {

    @Schema(description = "현재 페이지", example = "0")
    private int currentPage;

    @Schema(description = "페이지 사이즈", example = "5")
    private int size;

    @Schema(description = "전체 페이지", example = "11")
    private int totalPages;

    @Schema(description = "현재 페이지의 데이터 수", example = "5")
    private int currentPageElements;

    @Schema(description = "전체 데이터 수", example = "53")
    private long totalElements;

    @Schema(description = "이전 페이지 존재 여부", example = "false")
    private boolean hasPreviousPage;

    @Schema(description = "현재 페이지가 첫페이지인지 여부", example = "true")
    private boolean isFirstPage;

    @Schema(description = "다음 페이지 존재 여부", example = "true")
    private boolean hasNextPage;

    @Schema(description = "현재 페이지가 마지막페이지인지 여부", example = "false")
    private boolean isLastPage;

    @Schema(description = "프로젝트 리스트")
    private List<ProjectListElementDto> projectList;

    public static MainFeedResponse of(Page<Project> projectPage, List<ProjectListElementDto> projectDtoList) {
        return MainFeedResponse.builder()
                .currentPage(projectPage.getNumber()+1) // 1부터 시작
                .size(projectPage.getSize())
                .totalPages(projectPage.getTotalPages())
                .currentPageElements(projectPage.getNumberOfElements())
                .totalElements(projectPage.getTotalElements())
                .hasPreviousPage(projectPage.hasPrevious())
                .isFirstPage(projectPage.isFirst())
                .hasNextPage(projectPage.hasNext())
                .isLastPage(projectPage.isLast())
                .projectList(projectDtoList)
                .build();
    }

}
