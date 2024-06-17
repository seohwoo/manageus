package com.project.manageus.dto;


import com.project.manageus.entity.AttendanceEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AttendanceDTO {

    private Long id;
    private Long companyId;
    private Long userId;
    private Date date;
    private Date startTime;
    private Date endTime;
    private String note;


    @Builder
    public AttendanceDTO(Long id, Long companyId, Long userId, Date date, Date startTime, Date endTime, String note) {
        this.id = id;
        this.companyId = companyId;
        this.userId = userId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public AttendanceEntity toAttendanceEntity() {
        return AttendanceEntity.builder()
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
