package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="approval")
@DynamicInsert
public class ApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status_id")
    private Long statusId;
    private String title;
    @Column(name = "approval_type_id")
    private Long approvalTypeId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private String content;
    @Column(name = "sign_on")
    private Date signOn;
    @Column(name = "sign_off")
    private Date signOff;

    @Builder
    public ApprovalEntity(Long id, Long userId, Long statusId, String title, Long approvalTypeId,
                          Date startDate, Date endDate, String content, Date signOn, Date signOff) {
        super();
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.title = title;
        this.approvalTypeId = approvalTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.signOn = signOn;
        this.signOff = signOff;
    }

    public ApprovalDTO toApprovalDTO() {
        return ApprovalDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .statusId(this.statusId)
                .title(this.title)
                .approvalTypeId(this.approvalTypeId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .content(this.content)
                .signOn(this.signOn)
                .signOff(this.signOff)
                .build();
    } // 이거는 Entity를 DTO로 만드는 작업이다.
}     // DB에서 넘어올 때는 Entity로 넘어온다.
