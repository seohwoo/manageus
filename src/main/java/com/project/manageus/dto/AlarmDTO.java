package com.project.manageus.dto;


import com.project.manageus.entity.AlarmEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AlarmDTO {

    private Long id;
    private Long userId;
    private String subject;
    private Long reader;
    private Long readType;
    private Date readDate;

    @Builder
    public AlarmDTO(Long id, Long userId, String subject, Long reader, Long readType, Date readDate) {
        super();
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.reader = reader;
        this.readType = readType;
        this.readDate = readDate;
    }

        public AlarmEntity toAlarmEntity(){
            return AlarmEntity.builder()
                    .id(this.id)
                    .userId(this.userId)
                    .subject(this.subject)
                    .reader(this.reader)
                    .readType(this.readType)
                    .readDate(this.readDate)
                    .build();
        }

}
