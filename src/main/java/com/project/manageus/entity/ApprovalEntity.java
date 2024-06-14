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
@Table(name="Approval")
public class ApprovalEntity {
    @Id
    private int ID;
    private int DocumentID;
    private int StatusID;
    private int VacationID;
    private String Content;
    private String Subject;
    private Date StartDate;
    private Date FinishDate;

    @Builder
    public ApprovalEntity(int ID, int DocumentID, int StatusID, int VacationID, String Content,
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

    public ApprovalDTO toApprovalDTO() {
        return ApprovalDTO.builder()
                .ID(this.ID)
                .DocumentID(this.DocumentID)
                .StatusID(this.StatusID)
                .VacationID(this.VacationID)
                .Content(this.Content)
                .Subject(this.Subject)
                .StartDate(this.StartDate)
                .FinishDate(this.FinishDate)
                .build();
    } // 이거는 Entity를 DTO로 만드는 작업이다.
}     // DB에서 넘어올 때는 Entity로 넘어온다.
