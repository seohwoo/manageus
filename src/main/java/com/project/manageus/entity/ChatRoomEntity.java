package com.project.manageus.entity;

import com.project.manageus.dto.ChatRoomDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="chatroom")
public class ChatRoomEntity {
    @Id
    private int id;
    private String name;
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reg;

    @Builder
    public ChatRoomEntity(int id,String name,int status,Date reg){
        super();
        this.id=id;
        this.name=name;
        this.status=status;
        this.reg=reg;

    }
    public ChatRoomDTO toChatRoomDTO(){
        return ChatRoomDTO.builder()
                .id(this.id)
                .name(this.name)
                .status(this.status)
                .reg(this.reg)
                .build();
    }

}
