package com.project.manageus.dto;

import com.project.manageus.entity.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectDTO {

    private Long id;
    private Long companyId;
    private Long userId;
    private String name;
    private Date startDate;
    private Date endDate;

    @Builder
    public ProjectDTO(Long id, Long companyId, Long userId, String name, Date startDate, Date endDate){
        super();
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectEntity toProjectEntity(){
        return ProjectEntity.builder()
                .id(this.id)
                .companyId(this.companyId)
                .userId(this.userId)
                .name(this.name)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }



}
