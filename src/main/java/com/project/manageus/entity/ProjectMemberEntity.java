package com.project.manageus.entity;

import com.project.manageus.dto.ProjectMemberDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PROJECT_MEMBER")
@DynamicInsert
public class ProjectMemberEntity {

    @Id
    private Long id;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "user_id")
    private Long userId;

    @Builder
    public ProjectMemberEntity(Long id, Long projectId, Long userId){
        super();
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
    }

    public ProjectMemberDTO toProjectMemberDTO(){
        return ProjectMemberDTO.builder()
                .id(this.id)
                .projectId(this.projectId)
                .userId(this.userId)
                .build();
    }
}
