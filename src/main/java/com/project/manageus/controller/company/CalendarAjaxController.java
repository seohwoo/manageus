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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/ajax/calendar/*")
public class CalendarAjaxController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarAjaxController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/getMyCalendarList")
    public ResponseEntity<Map<String, Object>> getMyCalendarList(@RequestParam Long userId, @RequestParam Long calendarType){
        Map<String, Object> response = new HashMap<>();

        CalendarEntity calendarEntity = calendarService.getMyCalendarId(userId,calendarType);
        Long calendarId = calendarEntity.getId();
        List<CalendarDetailEntity> calendarDetailEntityList = calendarService.getCalendarDetailList(calendarId);

        response.put("calendarDetailEntityList",calendarDetailEntityList);
        response.put("calendarId",calendarId);



        return ResponseEntity.ok().body(response);
    }



    @PostMapping("/addCalendarDetail")
    public ResponseEntity<Map<String, Object>> addCalendarDetail(@RequestBody CalendarDetailDTO calendarDetailDTO){
        Map<String, Object> response = new HashMap<>();

        CalendarDetailEntity calendarDetailEntity = calendarService.addCalendarDetail(calendarDetailDTO);


        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getDepartmentCalendarList")
    public ResponseEntity<Map<String, Object>> getDepartmentCalendarList(@RequestParam Long departmentId, @RequestParam Long calendarType){
        Map<String, Object> response = new HashMap<>();
        CalendarEntity calendarEntity = calendarService.getDepartmentCalendarId(departmentId,calendarType);
        Long calendarId = calendarEntity.getId();
        List<CalendarDetailEntity> calendarDetailEntityList = calendarService.getCalendarDetailList(calendarId);

        response.put("calendarDetailEntityList",calendarDetailEntityList);
        response.put("calendarId",calendarId);



        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getCompanyCalendarList")
    public ResponseEntity<Map<String, Object>> getCompanyCalendarList(@RequestParam Long companyId, @RequestParam Long calendarType){
        Map<String, Object> response = new HashMap<>();
        CalendarEntity calendarEntity = calendarService.getCompanyCalendarId(companyId,calendarType);
        Long calendarId = calendarEntity.getId();
        List<CalendarDetailEntity> calendarDetailEntityList = calendarService.getCalendarDetailList(calendarId);

        response.put("calendarDetailEntityList",calendarDetailEntityList);
        response.put("calendarId",calendarId);



        return ResponseEntity.ok().body(response);
    }

}
