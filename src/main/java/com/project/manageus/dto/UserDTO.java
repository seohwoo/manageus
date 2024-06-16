package com.project.manageus.dto;

import com.project.manageus.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String password;
    private Long position_id;
    private Long company_id;
    private Long status_id;
    private Long auth_id;
    private Long department_id;
    private Date reg_date;

    @Builder
    public UserDTO(Long id, String password, Long position_id, Long company_id, Long status_id, Long auth_id, Long department_id, Date reg_date) {
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

    public UserEntity toUserEntity() {
        return UserEntity.builder()
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
