package com.project.manageus.entity;


import com.project.manageus.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
public class UserEntity {

    @Id
    private int id;
    private String password;
    @Column(name="position_id")
    private int positionId;
    @Column(name="company_id")
    private int companyId;
    @Column(name="status_id")
    private int statusId;
    @Column(name="auth_id")
    private int authId;
    @Column(name="department_id")
    private int departmentId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regdate;


    @Builder
    public UserEntity( int id,String password,int positionId,int companyId,int statusId,int authId,int departmentId,Date regdate) {
        super();
        this.id = id;
        this.password = password;
        this.positionId = positionId;
        this.companyId = companyId;
        this.statusId = statusId;
        this.authId = authId;
        this.departmentId = departmentId;
        this.regdate = regdate;
    }

    public UserDTO toUserEntity() {
        return UserDTO.builder()
                .id(this.id)
                .password(this.password)
                .positionId(this.positionId)
                .companyId(this.companyId)
                .statusId(this.statusId)
                .authId(this.authId)
                .departmentId(this.departmentId)
                .regdate(this.regdate)
                .build();
    }

}
