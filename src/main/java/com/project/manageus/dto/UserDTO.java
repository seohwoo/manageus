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
    private Long positionId;
    private Long companyId;
    private Long statusId;
    private Long authId;
    private Long departmentId;
    private Date regDate;

    @Builder
    public UserDTO(Long id, String password, Long positionId, Long companyId, Long statusId, Long authId, Long departmentId, Date regDate) {
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

    public UserEntity toUserEntity() {
        return UserEntity.builder()
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
