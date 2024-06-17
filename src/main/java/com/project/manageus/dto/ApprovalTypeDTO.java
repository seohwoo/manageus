package com.project.manageus.dto;

import com.project.manageus.entity.ApprovalTypeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApprovalTypeDTO {
    private Long id;
    private String name;

    @Builder
    public ApprovalTypeDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public ApprovalTypeEntity toApprovalTypeEntity() {
        return ApprovalTypeEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
