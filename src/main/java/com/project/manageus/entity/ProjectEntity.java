package com.project.manageus.entity;

import com.project.manageus.dto.ProjectDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PROJECT")
@DynamicInsert
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "user_id")
    private Long userId;
    private String name;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @Builder
    public ProjectEntity(Long id, Long companyId, Long userId, String name, Date startDate, Date endDate){
        super();
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectDTO toProjectDTO(){
        return ProjectDTO.builder()
                .id(this.id)
                .companyId(this.companyId)
                .userId(this.userId)
                .name(this.name)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }



}
