package com.project.manageus.controller.company;


import com.project.manageus.dto.CalendarDTO;
import com.project.manageus.entity.CalendarEntity;
import com.project.manageus.service.CalendarService;
import com.project.manageus.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor   //이게있으면 밑에 this를 안써도 된다 롬복기능
@RequestMapping("/companies/{companyId}/*")
public class CalendarController {

    private final CalendarService calendarService;
    private final UrlService urlService;



    @GetMapping("/calendar/{id}/myCalendar") /* 개인달력 */
    public String myCalendar(Model model, Principal principal, @PathVariable Long companyId,
                             @PathVariable Long id) {

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }
        Long calendarType = 2L;
        int myCalendarCount = calendarService.myCalendarCount(id,calendarType);
        System.out.println("달력카운트=============="+myCalendarCount);
        if(myCalendarCount == 0){
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setCompanyId(companyId);
            calendarDTO.setDepartmentId(calendarService.getUserInfo(id).getDepartmentId());
            calendarDTO.setUserId(id);
            calendarDTO.setCalendarType(calendarType);
            calendarService.addMyCalendar(calendarDTO);
            System.out.println("마이캘린더 등록 완료");
        }


        return "/company/calendars/mycalendar";
    }

    /*ajax 사용 팀원 달력 내용불러오기*/
    @GetMapping("/calendar/{id}/events")
    @ResponseBody
    public List<CalendarEntity> getEvents(@PathVariable Long id, @PathVariable Long companyId) {

        System.out.println("======="+ "정상작동"+id);
        System.out.println("======="+ "정상작동"+companyId);


        return calendarService.getEventsByUserId(id);
    }

    @GetMapping("/calendar/department/{departmentId}/teamCalendar") /* 부서달력 */
    public String teamCalendar(Model model, Principal principal, @PathVariable Long companyId,
                               @PathVariable Long departmentId) {

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
        ) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }
        Long calendarType = 1L;
        int teamCalendarCount = calendarService.teamCalendarCount(departmentId,calendarType);

        if(teamCalendarCount == 0){
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setCompanyId(companyId);
            calendarDTO.setDepartmentId(departmentId);
            calendarDTO.setCalendarType(calendarType);
            calendarService.addMyCalendar(calendarDTO);
        }


        return "/company/calendars/teamcalendar";
    }

    @GetMapping("/calendar/companyCalendar") /* 회사달력 */
    public String companyCalendar(Model model, Principal principal, @PathVariable Long companyId) {

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
        ) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }
        Long calendarType = 0L;
        int CompanyCalendarCount = calendarService.companyCalendarCount(companyId,calendarType);

        if(CompanyCalendarCount == 0){
            CalendarDTO calendarDTO = new CalendarDTO();
            calendarDTO.setCompanyId(companyId);
            calendarDTO.setCalendarType(calendarType);
            calendarService.addMyCalendar(calendarDTO);
        }


        return "/company/calendars/companycalendar";
    }

    @GetMapping("/calendar/{id}/{departmentId}")
    public String calendar(Model model, Principal principal, @PathVariable Long companyId,
                           @PathVariable Long departmentId, @PathVariable Long id) {

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }



        return "/company/calendars/calendar";
    }



}
