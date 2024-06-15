package com.project.manageus.dto;

import com.project.manageus.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String pw;
    private Long positionId;
    private Long companyId;
    private Long statusId;
    private Long authId;
    private Long departmentId;
    private Date regdate;

    @Builder
    public MemberDTO(Long id,String pw,Long positionId,Long companyId,Long statusId,Long authId,Long departmentId,Date regdate) {
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

    public MemberEntity toMemberEntity() {
        return MemberEntity.builder()
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
