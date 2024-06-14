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
@Table(name= "Calendar")
public class CalendarEntity {

    @Id
    private int id;
    private int companyId;
    private int departmentId ;
    private int memberId ;
    private int calendertype  ;

    @Builder
    public CalendarEntity(int id, int companyId, int departmentId, int memberId, int calendertype){
        super();
        this.id = id;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.memberId = memberId;
        this.calendertype = calendertype;
    }

    public CalendarDTO toCalendarDTO(){
        return CalendarDTO.builder()
                .id(this.id)
                .companyId(this.companyId)
                .departmentId(this.departmentId)
                .memberId(this.memberId)
                .calendertype(this.calendertype)
                .build();

    } //엔티티를 디티오로 바꾸는 작업

}
