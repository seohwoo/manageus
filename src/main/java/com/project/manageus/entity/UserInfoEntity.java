package com.project.manageus.entity;

import com.project.manageus.dto.UserInfoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER_INFO")
@DynamicInsert
public class UserInfoEntity {

    @Id
    private Long id;
    private String name;
    private String stamp;
    private String email;
    private String phone;
    private String address;
    private String birth;
    private String gender;



    @Builder
    public UserInfoEntity(Long id, String name, String stamp, String email, String phone, String address, String birth, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.stamp = stamp;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
    }

    public UserInfoDTO toUserInfoDTO() {
        return UserInfoDTO.builder()
                .id(this.id)
                .name(this.name)
                .stamp(this.stamp)
                .email(this.email)
                .phone(this.phone)
                .address(this.address)
                .birth(this.birth)
                .gender(this.gender)
                .build();
    }

}
