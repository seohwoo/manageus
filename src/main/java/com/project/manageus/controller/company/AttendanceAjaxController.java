package com.project.manageus.controller.company;

import com.project.manageus.dto.AttendanceDTO;
import com.project.manageus.entity.AttendanceEntity;

import com.project.manageus.service.AttendanceService;

import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/ajax/attendance/*")
public class AttendanceAjaxController {
    private final UrlService urlService;
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceAjaxController(UrlService urlService, AttendanceService attendanceService) {
        this.urlService = urlService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/getAttendance")
    public ResponseEntity<Map<String, Object>> getAttendance(Long userId){
        Map<String, Object> response = new HashMap<>();

        List<AttendanceEntity> attendanceList = attendanceService.getAttendanceList(userId);

        response.put("attendanceList", attendanceList);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/inCompany")
    public ResponseEntity<Map<String, Object>> inCompany(@RequestBody AttendanceDTO attendanceDTO){
        Map<String, Object> response = new HashMap<>();


        Date now = new Date();
        attendanceDTO.setDate(now);
        attendanceDTO.setStartTime(now);

        List<AttendanceEntity> attendanceList = attendanceService.getAttendanceList(attendanceDTO.getUserId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = dateFormat.format(now);
        String attendanceDateString = "";
        int sameResult = 0;
        for(AttendanceEntity s : attendanceList){
            attendanceDateString = dateFormat.format(s.getDate());
            System.out.println("디비속 날짜 ========"+attendanceDateString);
            if(attendanceDateString.equals(currentDateString)){
                sameResult = 1;
            }
        }




        System.out.println("결과 ========"+sameResult);
        if(sameResult == 0) {
            AttendanceEntity attendanceEntity = attendanceService.inCompany(attendanceDTO);
        }
        response.put("sameResult", sameResult);

        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/outCompany")
    public ResponseEntity<Map<String, Object>> outCompany(@RequestBody Map<String, Object> requestData){
        Long userId = Long.valueOf(requestData.get("userId").toString());
        Map<String, Object> response = new HashMap<>();
        System.out.println("userId=========="+userId);
        Date endTime = new Date();
        List<AttendanceEntity> attendanceList = attendanceService.getAttendanceList(userId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = dateFormat.format(endTime);
        Long id = -1L;
        String attendanceDateString;
        for(AttendanceEntity s : attendanceList){
            attendanceDateString = dateFormat.format(s.getDate());
            if(attendanceDateString.equals(currentDateString)) {
                if (s.getEndTime() != null) {
                    id = 0L;
                    break;
                }
                id = s.getId();
                break;
            }
        }
        System.out.println("id값========"+id);
        int outResult = 0;
        if(id.equals(-1L)){
            outResult = -1;
        } else if (id > 0) {
            outResult = attendanceService.outCompany(endTime,id);
        }

        response.put("outResult", outResult);

        return ResponseEntity.ok().body(response);
    }


}
