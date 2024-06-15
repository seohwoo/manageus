package com.project.manageus.dto;

import com.project.manageus.entity.ApprovalDetailEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApprovalDetailDTO {
    private int id;
    private int approvalId;
    private int userId;
    private int statusId;

    @Builder
    public ApprovalDetailDTO(int id, int approvalId, int userId, int statusId) {
        super();
        this.id = id;
        this.approvalId = approvalId;
        this.userId = userId;
        this.statusId = statusId;
    }

    public ApprovalDetailEntity toApprovalEntity() {
        return ApprovalDetailEntity.builder()
                .id(this.id)
                .approvalId(this.approvalId)
                .userId(this.userId)
                .statusId(this.statusId)
                .build();
    }
}
