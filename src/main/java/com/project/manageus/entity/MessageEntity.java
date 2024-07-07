package com.project.manageus.entity;


import com.project.manageus.dto.MessageDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="alarm")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id")
    private Long userID;
    private String subject;
    private Long reader;
    @Column(name="read_type")
    private Long readType;
    @CreationTimestamp
    @Column(name="read_date")
    private Date readDate;


    @Builder
    public MessageEntity(Long id, Long userID, String subject, Long reader, Long readType, Date readDate){
        super();
        this.id=id;
        this.userID=userID;
        this.subject=subject;
        this.reader=reader;
        this.readType=readType;
        this.readDate=readDate;
    }

    public MessageDTO toMessageDTO(){
        return MessageDTO.builder()
                .id(this.id)
                .userID(this.userID)
                .subject(this.subject)
                .reader(this.reader)
                .readType(this.readType)
                .readDate(this.readDate)
                .build();

    }//이거는 dto를 엔터티로 만드는 작업


}
