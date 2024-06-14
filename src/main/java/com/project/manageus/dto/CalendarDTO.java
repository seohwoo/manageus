package com.project.manageus.dto;

import com.project.manageus.entity.CalendarEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarDTO {

    private int id;
    private int companyId ;
    private int departmentId ;
    private int memberId ;
    private int calendertype  ;

    @Builder
    public CalendarDTO(int id, int companyId, int departmentId, int memberId, int calendertype){
        super();
        this.id = id;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.memberId = memberId;
        this.calendertype = calendertype;
    }

    public CalendarEntity toCalendarEntity(){
        return CalendarEntity.builder()
                .id(this.id)
                .companyId(this.companyId)
                .departmentId(this.departmentId)
                .memberId(this.memberId)
                .calendertype(this.calendertype)
                .build();

    } //이거는 dto를 엔터티로 만드는 작업

}
