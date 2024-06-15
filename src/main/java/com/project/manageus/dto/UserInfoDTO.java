package com.project.manageus.dto;

import com.project.manageus.entity.UserInfoEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
public class UserInfoDTO {

    @Id
    private int id;
    private String name;
    private String stamp;
    private String email;
    private String phone;
    private String address;
    private String birth;
    private String gender;


    @Builder
    public UserInfoDTO(int id, String name, String stamp, String phone, String address, String birth, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.stamp = stamp;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
    }

    public UserInfoEntity toUserInfoEntity() {
        return UserInfoEntity.builder()
                .id(this.id)
                .name(this.name)
                .stamp(this.stamp)
                .phone(this.phone)
                .address(this.address)
                .birth(this.birth)
                .gender(this.gender)
                .build();
    }

}
