package com.project.manageus.dto;

import com.project.manageus.entity.AuthEntity;
import com.project.manageus.entity.UserInfoEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class AuthDTO {


    private int id;
    private String name;


    @Builder
    public AuthDTO(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public AuthEntity toAuthEntity() {
        return AuthEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}
