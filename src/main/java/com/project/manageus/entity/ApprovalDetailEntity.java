package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDetailDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="approval_detail")
public class ApprovalDetailEntity {
    @Id
    private Long id;
    @Column(name = "approval_id")
    private Long approvalId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status_id")
    private Long statusId;

    @Builder
    public ApprovalDetailEntity(Long id, Long approvalId, Long userId, Long statusId) {
        super();
        this.id = id;
        this.approvalId = approvalId;
        this.userId = userId;
        this.statusId = statusId;
    }

    public ApprovalDetailDTO toApprovalDetailDTO() {
        return ApprovalDetailDTO.builder()
                .id(this.id)
                .approvalId(this.approvalId)
                .userId(this.userId)
                .statusId(this.statusId)
                .build();
    }
}
