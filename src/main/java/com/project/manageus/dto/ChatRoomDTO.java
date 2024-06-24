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
    private Long statusId;
    private Date reg;
    private Long departmentId;

    @Builder
    public ChatRoomDTO(Long id,String name,Long statusId,Date reg){
        super();
        this.id=id;
        this.name=name;
        this.statusId=statusId;
        this.reg=reg;

    }
    public ChatRoomEntity toChatRoomEntity(){
        return ChatRoomEntity.builder()
                .id(this.id)
                .name(this.name)
                .statusId(this.statusId)
                .reg(this.reg)
                .build();
    }
}
