package com.project.manageus.entity;

import com.project.manageus.dto.CalendarDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name= "Calendar")
public class CalendarEntity {

    @Id
    private Long id;
    @Column(name = "company_id")
    private Long companyId ;
    @Column(name = "department_id")
    private Long departmentId ;
    @Column(name = "user_id")
    private Long userId ;
    @Column(name = "calendar_type")
    private Long calendarType  ;

    @Builder
    public CalendarEntity(Long id, Long companyId, Long departmentId, Long userId, Long calendarType){
        super();
        this.id = id;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.userId = userId;
        this.calendarType = calendarType;
    }

    public CalendarDTO toCalendarDTO(){
        return CalendarDTO.builder()
                .id(this.id)
                .companyId(this.companyId)
                .departmentId(this.departmentId)
                .userId(this.userId)
                .calendarType(this.calendarType)
                .build();

    } //엔티티를 디티오로 바꾸는 작업

}
