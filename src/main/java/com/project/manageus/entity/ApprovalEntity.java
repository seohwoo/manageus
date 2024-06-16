package com.project.manageus.entity;

import com.project.manageus.dto.ApprovalDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="approval")
public class ApprovalEntity {
    @Id
    private Long id;
    private Long user_id;
    private Long status_id;
    private String title;
    private Long approval_type_id;
    private Date start_date;
    private Date end_date;
    private String content;
    private Date sign_on;
    private Date sign_off;

    @Builder
    public ApprovalEntity(Long id,Long user_id,Long status_id,String title,Long approval_type_id,Date start_date,Date end_date,String content,Date sign_on,Date sign_off) {
        super();
        this.id = id;
        this.user_id = user_id;
        this.status_id = status_id;
        this.title = title;
        this.approval_type_id = approval_type_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.content = content;
        this.sign_on=sign_on;
        this.sign_off=sign_off;
    }

    public ApprovalDTO toApprovalDTO() {
        return ApprovalDTO.builder()
                .id(this.id)
                .user_id(this.user_id)
                .status_id(this.status_id)
                .title(this.title)
                .approval_type_id(this.approval_type_id)
                .start_date(this.start_date)
                .end_date(this.end_date)
                .content(this.content)
                .sign_on(this.sign_on)
                .sign_off(this.sign_off)
                .build();
    } // 이거는 Entity를 DTO로 만드는 작업이다.
}     // DB에서 넘어올 때는 Entity로 넘어온다.
