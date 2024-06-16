package com.project.manageus.dto;


import com.project.manageus.entity.CalendarDetailEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CalendarDetailDTO {

    private Long id;
    private Date start_date;
    private Date end_date;
    private String content ;

    @Builder
    public CalendarDetailDTO(Long id, Date start_date, Date end_date, String content){
        this.id=id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.content=content;
    }
    public CalendarDetailEntity toCalendarDetailEntity(){
        return CalendarDetailEntity.builder()
                .id(this.id)
                .start_date(this.start_date)
                .end_date(this.end_date)
                .content(this.content)
                .build();
    }


}
