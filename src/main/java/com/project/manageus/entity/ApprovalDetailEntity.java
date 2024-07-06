package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDetailDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="approval_detail")
public class ApprovalDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
