package com.project.manageus.entity;


import com.project.manageus.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER")
@DynamicInsert
public class UserEntity {

    @Id
    private Long id;
    private String password;
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "auth_id")
    private Long authId;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "reg_date")
    private Date regDate;


    @Builder
    public UserEntity(Long id, String password, Long positionId, Long companyId, Long statusId, Long authId, Long departmentId, Date regDate) {
        super();
        this.id = id;
        this.password = password;
        this.positionId = positionId;
        this.companyId = companyId;
        this.statusId = statusId;
        this.authId = authId;
        this.departmentId = departmentId;
        this.regDate = regDate;
    }

    public UserDTO toUserDTO() {
        return UserDTO.builder()
                .id(this.id)
                .password(this.password)
                .positionId(this.positionId)
                .companyId(this.companyId)
                .statusId(this.statusId)
                .authId(this.authId)
                .departmentId(this.departmentId)
                .regDate(this.regDate)
                .build();
    }

}
