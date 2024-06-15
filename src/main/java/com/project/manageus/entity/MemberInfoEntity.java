package com.project.manageus.entity;

import com.project.manageus.dto.MemberInfoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "MEMBERINFO")
public class MemberInfoEntity {

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
    public MemberInfoEntity(Long id, String name, String stamp, String phone, String address, String birth, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.stamp = stamp;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
    }

    public MemberInfoDTO toMemberInfoDTO() {
        return MemberInfoDTO.builder()
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
