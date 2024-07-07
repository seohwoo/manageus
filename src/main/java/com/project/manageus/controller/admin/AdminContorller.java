package com.project.manageus.controller.admin;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.service.AdminService;
import com.project.manageus.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor    //lombok 생성자 주입
@RequestMapping("/admin/*")
public class AdminContorller {

    private final AdminService adminService;
    private final UrlService urlService;

    @GetMapping("/{companyId}")
    public String adminMain(@PathVariable Long companyId,
                            Principal principal,
                            Model model) {
        String url = "admin/main.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllEmployee(companyId, model));
        return url;
    }

    @GetMapping("{companyId}/employees")
    public String showAllEmployee(@PathVariable Long companyId,
                                  Principal principal,
                                  Model model) {
        String url = "admin/employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllEmployee(companyId, model));
        return url;
    }

    @PatchMapping("{companyId}/employees")
    public String updateUserInfo(@PathVariable Long companyId,
                                 Long userId,
                                 Long positionId,
                                 Long departmentId,
                                 Long statusId) {
        String url = "redirect:/admin/" + companyId + "/employees";
        if(!adminService.updateUserInfo(userId, positionId, departmentId, statusId)) {
            url = "redirect:/admin/" + companyId + "/employees";
        }
        return url;
    }

    @GetMapping("{companyId}/employees/pending")
    public String showAllPendingEmployee(@PathVariable Long companyId,
                                         Principal principal,
                                         Model model) {
        String url = "admin/pending-employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllPendingEmployee(companyId, model));
        return url;
    }

    @GetMapping("{companyId}/employees/exit")
    public String showExitUser(@PathVariable Long companyId,
                               Principal principal,
                               Model model) {
        String url = "/admin/exit-employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        model.addAttribute("existUser", adminService.findAllExitEmployee(companyId, model));
        return url;
    }

    @PatchMapping("{companyId}/employees/status")
    public String updateStatusUser(@PathVariable Long companyId,
                                   Long userId,
                                   Long statusId) {
        String url = "redirect:/admin/" + companyId + "/employees/pending";
        if(!adminService.updateUserStatus(userId, statusId)) {
            url = "redirect:/admin/" + companyId + "/employees/pending";
        }
        return url;
    }

    @GetMapping("{companyId}/departments")
    public String showDepartment(@PathVariable Long companyId,
                                 Principal principal,
                                 Model model) {
        String url = "admin/department.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        return url;
    }

    @GetMapping("{companyId}/departments/form")
    public String showDepartmentForm(@PathVariable Long companyId,
                                 Principal principal,
                                 Model model) {
        String url = "admin/department-create-form.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        return url;
    }

    @PostMapping("{companyId}/departments")
    public String insertDepartment(@PathVariable Long companyId,
                                   DepartmentDTO departmentDTO) {
        String url = "redirect:/admin/" + companyId + "/departments";
        if(!adminService.createDepartment(departmentDTO)) {
            url = "redirect:/admin/" + companyId + "/departments/form";
        }
        return url;
    }

    @GetMapping("{companyId}/profile")
    public String showCompanyProfile(@PathVariable Long companyId,
                                     Principal principal,
                                     Model model) {
        String url = "admin/profile";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        adminService.findCompanyInfo(companyId, model);
        return url;
    }

    @GetMapping("{companyId}/profile/form")
    public String showCompanyProfileForm(@PathVariable Long companyId,
                                     Principal principal,
                                     Model model) {
        String url = "admin/profile-update-form";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        adminService.findCompanyInfo(companyId, model);
        return url;
    }

    @PutMapping("{companyId}/profile")
    public String updateCompanyInfo(@PathVariable Long companyId,
                                    CompanyDTO companyDTO) {
        String url = "redirect:/admin/" + companyId + "/profile";
        if(!adminService.updateCompanyInfo(companyDTO)) {
            url = "redirect:/admin/" +companyId;
        }
        return url;
    }
}
