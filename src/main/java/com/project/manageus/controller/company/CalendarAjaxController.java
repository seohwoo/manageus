package com.project.manageus.controller.company;

import com.project.manageus.dto.CalendarDetailDTO;
import com.project.manageus.entity.CalendarDetailEntity;
import com.project.manageus.entity.CalendarEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.service.CalendarService;
import com.project.manageus.service.ProjectService;
import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajax/calendar/*")
public class CalendarAjaxController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarAjaxController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/getCalendarList")
    public ResponseEntity<Map<String, Object>> getCalendarList(@RequestParam Long userId, @RequestParam Long calendarType){
        Map<String, Object> response = new HashMap<>();

        System.out.println("유저아디 ======= "+userId);
        System.out.println("타입아이디 ======= "+calendarType);
        CalendarEntity calendarEntity = calendarService.getCalendarId(userId,calendarType);
        Long calendarId = calendarEntity.getId();
        System.out.println("캘린더아이디 ==============="+calendarId);
        List<CalendarDetailEntity> calendarDetailEntityList = calendarService.getCalendarDetailList(calendarId);

        response.put("calendarDetailEntityList",calendarDetailEntityList);
        response.put("calendarId",calendarId);



        return ResponseEntity.ok().body(response);
    }



    @PostMapping("/addCalendarDetail")
    public ResponseEntity<Map<String, Object>> addCalendarDetail(@RequestBody CalendarDetailDTO calendarDetailDTO){
        Map<String, Object> response = new HashMap<>();
        System.out.println("캘린더id======"+calendarDetailDTO.getCalendarId());
        System.out.println("시작날짜======"+calendarDetailDTO.getStartDate());
        System.out.println("마감날짜======"+calendarDetailDTO.getEndDate());
        System.out.println("내용======"+calendarDetailDTO.getContent());
        System.out.println("색깔======"+calendarDetailDTO.getColor());

        CalendarDetailEntity calendarDetailEntity = calendarService.addCalendarDetail(calendarDetailDTO);


        return ResponseEntity.ok().body(response);
    }

}
