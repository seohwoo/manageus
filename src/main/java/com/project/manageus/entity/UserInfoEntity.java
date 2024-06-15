package com.project.manageus.entity;

import com.project.manageus.dto.UserInfoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USERINFO")
public class UserInfoEntity {


    private int id;
    private String name;
    private String stamp;
    private String email;
    private String phone;
    private String address;
    private String birth;
    private String gender;


    @Builder
    public UserInfoEntity(int id, String name, String stamp, String phone, String address, String birth, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.stamp = stamp;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
    }

    public UserInfoDTO toUserInfoEntity() {
        return UserInfoDTO.builder()
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
