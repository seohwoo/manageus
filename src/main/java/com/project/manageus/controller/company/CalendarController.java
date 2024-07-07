package com.project.manageus.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class CalendarController {

    @GetMapping("/mycalendar") /* 개인달력 */
    public String mycalendar() {
        return "calendars/mycalendar.html";
    }

    @GetMapping("/companycalendar") /* 회사달력 */
    public String companycalendar () {
        return "calendars/companycalendar.html";
    }

    @GetMapping("/teamcalendar") /* 팀원 달력 */
    public String teamcalendar () {
        return "calendars/teamcalendar.html";
    }

}
