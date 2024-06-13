package com.project.manageus.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String repeatPassword;
    private String inviteCode;
    private String email;
    private String phone;
    private String address;
    private int birth;
    private int gender;
    private Date regdate;

    private int positionId;
    private int companyId;
    private int statusId;
    private int authId;
    private int departmentId;
    private int stamp;


}
