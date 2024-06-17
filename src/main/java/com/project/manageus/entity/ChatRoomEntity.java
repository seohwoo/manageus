package com.project.manageus.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="chat_room")
@DynamicInsert
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "status_id")
    private Long statusId;
    @CreationTimestamp
    @Column(name="reg")
    private Date reg;

    @Builder
    public ChatRoomEntity(Long id,String name,Long statusId,Date reg){
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
