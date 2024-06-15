package com.project.manageus.dto;

import com.project.manageus.entity.CompanyEntity;
import com.project.manageus.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyDTO {


    private int id;
    private String pw;
    private String businessNum;
    private String inviteCode;
    private String ceo;
    private String address;
    private String email;
    private int statusId;
    private int authId;
    private Date regdate;

    @Builder
    public CompanyDTO(int id, String pw, String businessNum,
                      String inviteCode, String ceo, String address,
                      String email, int statusId, int authId, Date regdate) {
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
