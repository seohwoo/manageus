package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalTypeDTO;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name="approvaltype")
public class ApprovalTypeEntity {
    @Id
    private int id;
    private String name;

    @Builder
    public ApprovalTypeEntity(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public ApprovalTypeDTO toApprovalTypeDTO() {
        return ApprovalTypeDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
