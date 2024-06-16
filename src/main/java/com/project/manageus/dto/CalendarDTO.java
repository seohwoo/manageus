package com.project.manageus.dto;

import com.project.manageus.entity.CalendarEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarDTO {

    private Long id;
    private Long company_id;
    private Long department_id;
    private Long user_id;
    private Long calender_type;

    @Builder
    public CalendarDTO(Long id, Long company_id, Long department_id, Long user_id, Long calender_type){
        super();
        this.id = id;
        this.company_id = company_id;
        this.department_id = department_id;
        this.user_id = user_id;
        this.calender_type = calender_type;
    }

    public CalendarEntity toCalendarEntity(){
        return CalendarEntity.builder()
                .id(this.id)
                .company_id(this.company_id)
                .department_id(this.department_id)
                .user_id(this.user_id)
                .calender_type(this.calender_type)
                .build();

    } //이거는 dto를 엔터티로 만드는 작업

}
