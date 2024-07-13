package com.project.manageus.entity;

import com.project.manageus.dto.CalendarDetailDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


    @Data
    @NoArgsConstructor
    @Entity
    @Table(name= "calendar_detail")
    @DynamicInsert   //인서트시에 사용
    @DynamicUpdate
    public class CalendarDetailEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "calendar_id")
        private Long calendarId;
        @Column(name = "start_date")
        private Date startDate;
        @Column(name = "end_date")
        private Date endDate;
        private String content;
        private String color;

        @Builder
        public CalendarDetailEntity(Long id, Long calendarId, Date startDate, Date endDate, String content, String color){
            super();
            this.id = id;
            this.calendarId = calendarId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.content = content;
            this.color = color;
        }

        public CalendarDetailDTO toCalendarDetailDTO(){
            return CalendarDetailDTO.builder()
                    .id(this.id)
                    .calendarId(this.calendarId)
                    .startDate(this.startDate)
                    .endDate(this.endDate)
                    .content(this.content)
                    .color(this.color)
                    .build();

        }
}
