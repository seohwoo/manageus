package com.project.manageus.dto;

import com.project.manageus.entity.ChatRoomEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ChatRoomDTO {
    private Long id;
    private String name;
    private Long status_id;
    private Date reg;

    @Builder
    public ChatRoomDTO(Long id,String name,Long status_id,Date reg){
        super();
        this.id=id;
        this.name=name;
        this.status_id=status_id;
        this.reg=reg;

    }
    public ChatRoomEntity toChatRoomEntity(){
        return ChatRoomEntity.builder()
                .id(this.id)
                .name(this.name)
                .status_id(this.status_id)
                .reg(this.reg)
                .build();
    }
}
