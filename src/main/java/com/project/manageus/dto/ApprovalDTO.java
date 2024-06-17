package com.project.manageus.dto;

import com.project.manageus.entity.ApprovalEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor // 기본 생성자 자동 생성 필수.
public class ApprovalDTO {
    private Long id;
    private Long userId;
    private Long statusId;
    private String title;
    private Long approvalTypeId;
    private Date startDate;
    private Date endDate;
    private String content;
    private Date signOn;
    private Date signOff;

    @Builder
    public ApprovalDTO(Long id, Long userId, Long statusId, String title, Long approvalTypeId,
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

    public ApprovalEntity toApprovalEntity() {
        return ApprovalEntity.builder()
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
    } // 이거는 DTO를 Entity로 만드는 작업이다.
}     // 값을 받아와서 넘길 때는 DTO에서 Entity로 넘어간다.


// DB에서 꺼내올 떄
//  - Entity - DTO - ApprovalJPARepository - Service - ServiceImpl - Controller - view
// 값 넘겨줄 때
//  - view - Controller - ServiceImpl - Service - ApprovalJPARepository - DTO - Entity


