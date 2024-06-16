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
    private Long id;
    private String password;
    private Long position_id;
    private Long company_id;
    private Long status_id;
    private Long auth_id;
    private Long department_id;
    private Date reg_date;


    @Builder
    public UserEntity(Long id, String password, Long position_id, Long company_id, Long status_id, Long auth_id, Long department_id, Date reg_date) {
        super();
        this.id = id;
        this.password = password;
        this.position_id = position_id;
        this.company_id = company_id;
        this.status_id = status_id;
        this.auth_id = auth_id;
        this.department_id = department_id;
        this.reg_date = reg_date;
    }

    public UserDTO toUserDTO() {
        return UserDTO.builder()
                .id(this.id)
                .password(this.password)
                .position_id(this.position_id)
                .company_id(this.company_id)
                .status_id(this.status_id)
                .auth_id(this.auth_id)
                .department_id(this.department_id)
                .reg_date(this.reg_date)
                .build();
    }

}


