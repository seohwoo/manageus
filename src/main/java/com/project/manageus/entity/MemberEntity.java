package com.project.manageus.entity;


import com.project.manageus.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MEMBER")
@DynamicInsert
public class MemberEntity {

    @Id
    private Long id;
    private String pw;
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "status_id", columnDefinition = "int default 1002")
    private Long statusId;
    @Column(name = "auth_id", columnDefinition = "int default 3")
    private Long authId;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "regdate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regdate;


    @Builder
    public MemberEntity(Long id,String pw,Long positionId,Long companyId,Long statusId,Long authId,Long departmentId,Date regdate) {
        super();
        this.id = id;
        this.pw = pw;
        this.positionId = positionId;
        this.companyId = companyId;
        this.statusId = statusId;
        this.authId = authId;
        this.departmentId = departmentId;
        this.regdate = regdate;
    }

    public MemberDTO toMemberDTO() {
        return MemberDTO.builder()
                .id(this.id)
                .pw(this.pw)
                .positionId(this.positionId)
                .companyId(this.companyId)
                .statusId(this.statusId)
                .authId(this.authId)
                .departmentId(this.departmentId)
                .regdate(this.regdate)
                .build();
    }

}
