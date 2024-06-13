package com.project.manageus.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

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
