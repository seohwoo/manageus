package com.project.manageus.entity;

import com.project.manageus.dto.CompanyDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COMPANY")
public class CompanyEntity {

    @Id
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
    public CompanyEntity(int id, String pw, String businessNum,
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

    public CompanyDTO toCompanyDTO() {
        return CompanyDTO.builder()
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
