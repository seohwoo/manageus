package com.project.manageus.entity;

import com.project.manageus.dto.CompanyDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COMPANY")
@DynamicInsert
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pw;
    private String name;
    @Column(name = "business_num")
    private String businessNum;
    @Column(name = "invite_code")
    private String inviteCode;
    private String ceo;
    private String address;
    @Column(unique = true)
    private String email;
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "auth_id")
    private Long authId;
    @Column(name = "reg_date")
    private Date regDate;


    @Builder
    public CompanyEntity(Long id, String pw, String name, String businessNum,
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

    public CompanyDTO toCompanyDTO() {
        return CompanyDTO.builder()
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
