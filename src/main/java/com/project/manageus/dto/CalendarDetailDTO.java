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
        private Date startDate ;
        private Date endDate ;
        private String content ;

        @Builder
        public CalendarDetailDTO(Long id, Date startDate, Date endDate, String content){
            super();
            this.id = id;
            this.startDate = startDate;
            this.endDate = endDate;
            this.content = content;
        }

        public CalendarDetailEntity toCalendarDetailEntity(){
            return CalendarDetailEntity.builder()
                    .id(this.id)
                    .startDate(this.startDate)
                    .endDate(this.endDate)
                    .content(this.content)
                    .build();

        }



}
