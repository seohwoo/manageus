package com.project.manageus.dto;

import com.project.manageus.entity.ApprovalDetailEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApprovalDetailDTO {
    private Long id;
    private Long approvalId;
    private Long userId;
    private Long statusId;

    @Builder
    public ApprovalDetailDTO(Long id, Long approvalId, Long userId, Long statusId) {
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
