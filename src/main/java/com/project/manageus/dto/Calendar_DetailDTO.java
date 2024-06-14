package com.project.manageus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Calendar_DetailDTO {

    private int id;
    private Date startdate ;
    private Date enddate ;
    private String content ;


}
