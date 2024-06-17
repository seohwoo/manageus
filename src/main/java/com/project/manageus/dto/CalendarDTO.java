package com.project.manageus.dto;

import com.project.manageus.entity.CalendarEntity;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarDTO {

    private Long id;
    private Long companyId ;
    private Long departmentId ;
    private Long userId ;
    private Long calendarType  ;

    @Builder
    public CalendarDTO(Long id, Long companyId, Long departmentId, Long userId, Long calendarType){
        super();
        this.id = id;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.userId = userId;
        this.calendarType = calendarType;
    }

    public CalendarEntity toCalendarEntity(){
        return CalendarEntity.builder()
                .id(this.id)
                .companyId(this.companyId)
                .departmentId(this.departmentId)
                .userId(this.userId)
                .calendarType(this.calendarType)
                .build();

    } //이거는 dto를 엔터티로 만드는 작업

}
