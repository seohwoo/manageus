package com.project.manageus.controller.company;


import com.project.manageus.service.CalendarService;
import com.project.manageus.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor   //이게있으면 밑에 this를 안써도 된다 롬복기능
@RequestMapping("/companies/{companyId}/*")
public class CalendarController {

    private final CalendarService calendarService;
    private final UrlService urlService;


    @GetMapping("/calendar/{id}/mycalendar") /* 개인달력 */
    public String mycalendar(Model model, Principal principal, @PathVariable Long companyId,
                             @PathVariable Long id) {

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        return "/company/calendars/mycalendar";
    }





    @GetMapping("/companycalendar") /* 회사달력 */
    public String companycalendar() {
        return "calendars/companycalendar.html";
    }




    @GetMapping("/teamcalendar") /* 팀원 달력 */
    public String teamcalendar () {
        return "calendars/teamcalendar.html";
    }

}
