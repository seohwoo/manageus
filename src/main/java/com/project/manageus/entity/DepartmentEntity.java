package com.project.manageus.entity;


import com.project.manageus.dto.DepartmentDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "DEPARTMENT")
public class DepartmentEntity {

    @Id
    private Long id;
    private String name;
    @Column(name = "company_id")
    private Long companyId;


    @Builder
    public DepartmentEntity(Long id, String name, Long companyId) {
        super();
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }

    public DepartmentDTO toDepartmentDTO() {
        return DepartmentDTO.builder()
                .id(this.id)
                .name(this.name)
                .companyId(this.companyId)
                .build();
    }
}
