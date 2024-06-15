package com.project.manageus.dto;

import com.project.manageus.entity.CompanyEntity;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CompanyDTO {


    private Long id;
    private String pw;
    private String businessNum;
    private String inviteCode;
    private String ceo;
    private String address;
    private String email;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Long statusId;
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private Long authId;
    private Date regdate;

    @Builder
    public CompanyDTO(Long id, String pw, String businessNum,
                      String inviteCode, String ceo, String address,
                      String email, Long statusId, Long authId, Date regdate) {
        super();
        this.id = id;
        this.pw = pw;
        this.businessNum = businessNum;
        this.inviteCode = inviteCode;
        this.ceo = ceo;
        this.address = address;
        this.email = email;
        this.statusId = statusId;
        this.authId = authId;
        this.regdate = regdate;
    }

    public CompanyEntity toCompanyEntity() {
        return CompanyEntity.builder()
                .id(this.id)
                .pw(this.pw)
                .businessNum(this.businessNum)
                .inviteCode(this.inviteCode)
                .ceo(this.ceo)
                .address(this.address)
                .email(this.email)
                .statusId(this.statusId)
                .authId(this.authId)
                .regdate(this.regdate)
                .build();
    }


}
