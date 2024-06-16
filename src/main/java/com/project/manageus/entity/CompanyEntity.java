package com.project.manageus.entity;

import com.project.manageus.dto.CompanyDTO;
import jakarta.persistence.Column;
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
    private Long id;
    private String pw;
    private String business_num;
    private String invite_code;
    private String ceo;
    private String address;
    private String email;
    private Long status_id;
    private Long auth_id;
    private Date reg_date;


    @Builder
    public CompanyEntity(Long id, String pw, String business_num,
                      String invite_code, String ceo, String address,
                      String email, Long status_id, Long auth_id, Date reg_date) {
        super();
        this.id = id;
        this.pw = pw;
        this.business_num = business_num;
        this.invite_code = invite_code;
        this.ceo = ceo;
        this.address = address;
        this.email = email;
        this.status_id = status_id;
        this.auth_id = auth_id;
        this.reg_date = reg_date;
    }

    public CompanyDTO toCompanyDTO() {
        return CompanyDTO.builder()
                .id(this.id)
                .pw(this.pw)
                .business_num(this.business_num)
                .invite_code(this.invite_code)
                .ceo(this.ceo)
                .address(this.address)
                .email(this.email)
                .status_id(this.status_id)
                .auth_id(this.auth_id)
                .reg_date(this.reg_date)
                .build();
    }
}
