package com.project.manageus.entity;


import com.project.manageus.dto.AttendanceDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name= "ATTENDANCE")
public class AttendanceEntity {

    @Id
    private Long id;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "user_id")
    private Long userId;
    private Date date;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    private String note;

    @Builder
    public AttendanceEntity(Long id, Long companyId, Long userId, Date date, Date startTime, Date endTime, String note) {
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public AttendanceDTO toAttendanceDTO() {
        return AttendanceDTO.builder()
                .id(this.id)
                .companyId(this.companyId)
                .userId(this.userId)
                .date(this.date)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .note(this.note)
                .build();
    }

}
