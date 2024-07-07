package com.project.manageus.controller.company;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.service.AttendanceService;
import com.project.manageus.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/companies/{companyId}/users/*")
public class AttendanceController {

    private final UrlService urlService;
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(UrlService urlService, AttendanceService attendanceService) {
        this.urlService = urlService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{userId}/attendance")
    public String attendanceMain(@PathVariable Long companyId, @PathVariable Long userId, Principal principal, Model model) {
        String url = "/company/attendance/attendanceMain.html";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }

        return url;
    }

}
