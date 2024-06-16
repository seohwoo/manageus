package com.project.manageus.entity;

import com.project.manageus.dto.CalendarDetailDTO;
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
@Table(name="calendar_detail")
public class CalendarDetailEntity {
    @Id
    private Long id;
    private Date start_date;
    private Date end_date;
    private String content ;

    @Builder
    public CalendarDetailEntity(Long id, Date start_date, Date end_date, String content){
        this.id=id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.content=content;
    }
    public CalendarDetailDTO toCalendarDetailDTO(){
        return CalendarDetailDTO.builder()
                .id(this.id)
                .start_date(this.start_date)
                .end_date(this.end_date)
                .content(this.content)
                .build();
    }
}
