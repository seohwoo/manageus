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
    private Long companyId;

    @Builder
    public DepartmentDTO(Long id, String name, Long companyId) {
        super();
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }

    public DepartmentEntity toDepartmentEntity() {
        return DepartmentEntity.builder()
                .id(this.id)
                .name(this.name)
                .companyId(this.companyId)
                .build();
    }
}
