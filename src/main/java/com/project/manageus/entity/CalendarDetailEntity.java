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
        @UpdateTimestamp
        @Column(name = "start_date")
        private Date startDate;
        @UpdateTimestamp
        @Column(name = "end_date")
        private Date endDate;
        private String content;

        @Builder
        public CalendarDetailEntity(Long id, Date startDate, Date endDate, String content){
            super();
            this.id = id;
            this.startDate = startDate;
            this.endDate = endDate;
            this.content = content;
        }

        public CalendarDetailDTO toCalendarDetailDTO(){
            return CalendarDetailDTO.builder()
                    .id(this.id)
                    .startDate(this.startDate)
                    .endDate(this.endDate)
                    .content(this.content)
                    .build();

        }
}
