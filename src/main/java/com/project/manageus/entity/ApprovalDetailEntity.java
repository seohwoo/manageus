package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDetailDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="approvaldetail")
public class ApprovalDetailEntity {
    @Id
    private int id;
    private int approvalId;
    private int userId;
    private int statusId;

    @Builder
    public ApprovalDetailEntity(int id, int approvalId, int userId, int statusId) {
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
