package com.project.manageus.entity;


import com.project.manageus.dto.DepartmentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long company_id;

    @Builder
    public DepartmentEntity(Long id, String name, Long company_id) {
        super();
        this.id = id;
        this.name = name;
        this.company_id = company_id;
    }

    public DepartmentDTO toDepartmentDTO() {
        return DepartmentDTO.builder()
                .id(this.id)
                .name(this.name)
                .company_id(this.company_id)
                .build();
    }
}
