package com.project.manageus.entity;

import com.project.manageus.dto.ProjectDetailDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PROJECT_DETAIL")
@DynamicInsert
public class ProjectDetailEntity {

    private Long id;
    private String title;
    private String content;
    @Column(name = "status_id")
    private Long statusId;
    private String leader;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;

    @Builder
    public ProjectDetailEntity(Long id, String title, String content, Long statusId, String leader, Date startTime, Date endTime){
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.statusId = statusId;
        this.leader = leader;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ProjectDetailDTO toProjectDetailDTO(){
        return ProjectDetailDTO.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .statusId(this.statusId)
                .leader(this.leader)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }


}
