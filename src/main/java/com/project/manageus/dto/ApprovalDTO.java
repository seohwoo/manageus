package com.project.manageus.dto;

import com.project.manageus.entity.ApprovalEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor // 기본 생성자 자동 생성 필수.
public class ApprovalDTO {
    private int ID;
    private int DocumentID;
    private int StatusID;
    private int VacationID;
    private String Content;
    private String Subject;
    private Date StartDate;
    private Date FinishDate;

    @Builder
    public ApprovalDTO(int ID, int DocumentID, int StatusID, int VacationID, String Content,
    String Subject, Date StartDate, Date FinishDate) {
        super();
        this.ID = ID;
        this.DocumentID = DocumentID;
        this.StatusID = StatusID;
        this.VacationID = VacationID;
        this.Content = Content;
        this.Subject = Subject;
        this.StartDate = StartDate;
        this.FinishDate = FinishDate;
    }

    public ApprovalEntity toApprovalEntity() {
        return ApprovalEntity.builder()
                .ID(this.ID)
                .DocumentID(this.DocumentID)
                .StatusID(this.StatusID)
                .VacationID(this.VacationID)
                .Content(this.Content)
                .Subject(this.Subject)
                .StartDate(this.StartDate)
                .FinishDate(this.FinishDate)
                .build();
    } // 이거는 DTO를 Entity로 만드는 작업이다.
}     // 값을 받아와서 넘길 때는 DTO에서 Entity로 넘어간다.


// DB에서 꺼내올 떄
//  - Entity - DTO - ApprovalJPARepository - Service - ServiceImpl - Controller - view
// 값 넘겨줄 때
//  - view - Controller - ServiceImpl - Service - ApprovalJPARepository - DTO - Entity


