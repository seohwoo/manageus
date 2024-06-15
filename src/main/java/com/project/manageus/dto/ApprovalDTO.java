package com.project.manageus.dto;

import com.project.manageus.entity.ApprovalEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor // 기본 생성자 자동 생성 필수.
public class ApprovalDTO {
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
    public ApprovalDTO(int id, int userId, int statusId, String title, int approvalTypeId,
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

    public ApprovalEntity toApprovalEntity() {
        return ApprovalEntity.builder()
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
    } // 이거는 DTO를 Entity로 만드는 작업이다.
}     // 값을 받아와서 넘길 때는 DTO에서 Entity로 넘어간다.


// DB에서 꺼내올 떄
//  - Entity - DTO - ApprovalJPARepository - Service - ServiceImpl - Controller - view
// 값 넘겨줄 때
//  - view - Controller - ServiceImpl - Service - ApprovalJPARepository - DTO - Entity


