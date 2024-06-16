package com.project.manageus.entity;

import com.project.manageus.dto.ChatRoomDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
    private Long status_id;
    @CreationTimestamp
    @Column(name="reg")
    private Date reg;

    @Builder
    public ChatRoomEntity(Long id,String name,Long status_id,Date reg){
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
