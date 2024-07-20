package com.project.manageus.dto;

import com.project.manageus.entity.ProjectDetailEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectMemberDTO {
    private Long id;
    private Long projectId;
    private Long userId;

    @Builder
    public ProjectMemberDTO(Long id, Long projectId, Long userId){
        super();
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
    }

    public ProjectMemberEntity toProjectMemberEntity(){
        return ProjectMemberEntity.builder()
                .id(this.id)
                .projectId(this.projectId)
                .userId(this.userId)
                .build();
    }

}
