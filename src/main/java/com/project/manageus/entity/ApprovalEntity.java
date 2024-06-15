package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="approval")
public class ApprovalEntity {
    @Id
    private int id;
    private int userId;
    private int statusId;
    private String title;
    private int approvalTypeId;
    private String content;
    private Date startDate;
    private Date endDate;
    private Date signOn;
    private Date singOff;

    @Builder
    public ApprovalEntity(int id, int userId, int statusId, String title, int approvalTypeId,
                          String content, Date startDate, Date endDate, Date signOn, Date singOff) {
        super();
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.title = title;
        this.approvalTypeId = approvalTypeId;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.signOn = signOn;
        this.singOff = singOff;
    }

    public ApprovalDTO toApprovalDTO() {
        return ApprovalDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .statusId(this.statusId)
                .title(this.title)
                .approvalTypeId(this.approvalTypeId)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .signOn(this.signOn)
                .singOff(this.singOff)
                .build();
    } // 이거는 Entity를 DTO로 만드는 작업이다.
}     // DB에서 넘어올 때는 Entity로 넘어온다.
