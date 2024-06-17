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
    @Column(name = "member_id")
    private Long memberId ;
    @Column(name = "calender_type")
    private Long calenderType  ;

    @Builder
    public CalendarEntity(Long id, Long companyId, Long departmentId, Long memberId, Long calenderType){
        super();
        this.id = id;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.memberId = memberId;
        this.calenderType = calenderType;
    }

    public CalendarDTO toCalendarDTO(){
        return CalendarDTO.builder()
                .id(this.id)
                .companyId(this.companyId)
                .departmentId(this.departmentId)
                .memberId(this.memberId)
                .calenderType(this.calenderType)
                .build();

    } //엔티티를 디티오로 바꾸는 작업

}
