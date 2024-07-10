package com.project.manageus.dto;

import lombok.Data;

@Data
public class ChatCheckDTO {
    private String name;
    private int count;
    private Long chatRoomId;
    private Long id;
    private Long companyId;
}
