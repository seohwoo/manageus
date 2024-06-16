package com.project.manageus.entity;

import com.project.manageus.dto.CalendarDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="calendar")
public class CalendarEntity {

    @Id
    private Long id;
    private Long company_id;
    private Long department_id;
    private Long user_id;
    private Long calender_type  ;

    @Builder
    public CalendarEntity(Long id, Long company_id, Long department_id, Long user_id, Long calender_type){
        super();
        this.id = id;
        this.company_id = company_id;
        this.department_id = department_id;
        this.user_id = user_id;
        this.calender_type = calender_type;
    }

    public CalendarDTO toCalendarDTO(){
        return CalendarDTO.builder()
                .id(this.id)
                .company_id(this.company_id)
                .department_id(this.department_id)
                .user_id(this.user_id)
                .calender_type(this.calender_type)
                .build();

    } //엔티티를 디티오로 바꾸는 작업

}
