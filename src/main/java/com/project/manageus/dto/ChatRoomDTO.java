package com.project.manageus.dto;

import com.project.manageus.entity.ChatRoomEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ChatRoomDTO {
    private int id;
    private String name;
    private int status;
    private Date reg;

    @Builder
    public ChatRoomDTO(int id,String name,int status,Date reg){
        super();
        this.id=id;
        this.name=name;
        this.status=status;
        this.reg=reg;

    }
    public ChatRoomEntity toChatRoomEntity(){
        return ChatRoomEntity.builder()
                .id(this.id)
                .name(this.name)
                .status(this.status)
                .reg(this.reg)
                .build();
    }
}
