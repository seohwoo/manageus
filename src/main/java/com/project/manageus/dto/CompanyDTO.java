package com.project.manageus.dto;

import com.project.manageus.entity.CompanyEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CompanyDTO {

    private Long id;
    private String pw;
    private String name;
    private String businessNum;
    private String inviteCode;
    private String ceo;
    private String address;
    private String email;
    private Long statusId;
    private Long authId;
    private Date regDate;

    @Builder
    public CompanyDTO(Long id, String pw, String name, String businessNum,
                      String inviteCode, String ceo, String address,
                      String email, Long statusId, Long authId, Date regDate) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.businessNum = businessNum;
        this.inviteCode = inviteCode;
        this.ceo = ceo;
        this.address = address;
        this.email = email;
        this.statusId = statusId;
        this.authId = authId;
        this.regDate = regDate;
    }

    public CompanyEntity toCompanyEntity() {
        return CompanyEntity.builder()
                .id(this.id)
                .pw(this.pw)
                .name(this.name)
                .businessNum(this.businessNum)
                .inviteCode(this.inviteCode)
                .ceo(this.ceo)
                .address(this.address)
                .email(this.email)
                .statusId(this.statusId)
                .authId(this.authId)
                .regDate(this.regDate)
                .build();
    }


}
