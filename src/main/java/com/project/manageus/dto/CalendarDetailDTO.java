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
        private Long calendarId;
        private Date startDate ;
        private Date endDate ;
        private String content ;
        private String color;

        @Builder
        public CalendarDetailDTO(Long id, Long calendarId, Date startDate, Date endDate, String content, String color){
            super();
            this.id = id;
            this.calendarId = calendarId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.content = content;
            this.color = color;
        }

        public CalendarDetailEntity toCalendarDetailEntity(){
            return CalendarDetailEntity.builder()
                    .id(this.id)
                    .calendarId(this.calendarId)
                    .startDate(this.startDate)
                    .endDate(this.endDate)
                    .content(this.content)
                    .color(this.color)
                    .build();

        }



}
