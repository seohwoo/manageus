package com.project.manageus.dto;

import com.project.manageus.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO {

    private int id;
    private String password;
    private int positionId;
    private int companyId;
    private int statusId;
    private int authId;
    private int departmentId;
    private Date regdate;

    @Builder
    public UserDTO( int id,String password,int positionId,int companyId,int statusId,int authId,int departmentId,Date regdate) {
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

    public UserEntity toUserEntity() {
        return UserEntity.builder()
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
