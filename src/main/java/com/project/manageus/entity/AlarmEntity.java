package com.project.manageus.entity;

import com.project.manageus.dto.AlarmDTO;
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
public class AlarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id")
    private Long userId;
    private String subject;
    private Long reader;
    @Column(name="read_type")
    private Long readType;
    @CreationTimestamp
    @Column(name="read_date")
    private Date readDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserInfoEntity userInfo;  //보낸사람이 나일떄

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader", referencedColumnName = "id", insertable = false, updatable = false)
    private UserInfoEntity userInfos;   //받은살마이 나일때

    @Builder
    public AlarmEntity(Long id, Long userId, String subject, Long reader, Long readType, Date readDate){
        super();
        this.id=id;
        this.userId=userId;
        this.subject=subject;
        this.reader=reader;
        this.readType=readType;
        this.readDate=readDate;
    }

    public AlarmDTO toAlarmDTO(){
        return AlarmDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .subject(this.subject)
                .reader(this.reader)
                .readType(this.readType)
                .readDate(this.readDate)
                .build();
    }


}
