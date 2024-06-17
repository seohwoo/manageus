package com.project.manageus.dto;

import com.project.manageus.entity.ProjectDetailEntity;
import com.project.manageus.entity.ProjectEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectDetailDTO {

    private Long id;
    private String title;
    private String content;
    private Long statusId;
    private Long userId;
    private Date startTime;
    private Date endTime;

    @Builder
    public ProjectDetailDTO(Long id, String title, String content, Long statusId, Long userId, Date startTime, Date endTime){
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.statusId = statusId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ProjectDetailEntity toProjectDetailEntity(){
        return ProjectDetailEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .statusId(this.statusId)
                .userId(this.userId)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }
}
