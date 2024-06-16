package com.project.manageus.dto;

import com.project.manageus.entity.DepartmentEntity;
import com.project.manageus.entity.PositionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;
    private String name;
    private Long company_id;

    @Builder
    public DepartmentDTO(Long id, String name, Long company_id) {
        super();
        this.id = id;
        this.name = name;
        this.company_id = company_id;
    }

    public DepartmentEntity toDepartmentEntity() {
        return DepartmentEntity.builder()
                .id(this.id)
                .name(this.name)
                .company_id(this.company_id)
                .build();
    }
}
