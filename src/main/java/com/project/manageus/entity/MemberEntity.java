package com.project.manageus.entity;


import com.project.manageus.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MEMBER")
public class MemberEntity {

    @Id
    private Long id;
    private String pw;
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Long positionId;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Long companyId;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Long statusId;
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private Long authId;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Long departmentId;
    @Temporal(TemporalType.TIMESTAMP)
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
