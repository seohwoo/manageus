package com.project.manageus.controller.admin;

import com.project.manageus.dto.CompanyDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.dto.UserDTO;
import com.project.manageus.service.AdminService;
import com.project.manageus.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor    //lombok 생성자 주입
@RequestMapping("/admin/companies/*")
public class AdminContorller {

    private final AdminService adminService;
    private final UrlService urlService;

    @GetMapping("/{companyId}")
    public String adminMain(@PathVariable Long companyId,
                            Principal principal,
                            Model model) {
        String url = "admin/main.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        return url;
    }

    @GetMapping("{companyId}/users")
    public String showAllEmployee(@PathVariable Long companyId,
                                  @RequestParam Long statusId,
                                  Principal principal,
                                  Model model) {
        String url = "admin/employee/employee.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        if(statusId == 1001L) {
            url = "admin/employee/pending-employee.html";
        } else if (statusId == 1003L) {
            url = "admin/employee/exit-employee.html";
        }
        adminService.findAllEmployee(companyId, statusId, model);
        return url;
    }

    @PatchMapping("{companyId}/users/{id}")
    public String updateUserInfo(@PathVariable Long companyId,
                                 @PathVariable Long id,
                                 UserDTO userDTO) {
        String url = "redirect:/admin/companies/" + companyId + "/users?statusId=" + adminService.findStatusIdById(id);
        if(adminService.updateUser(userDTO)) {
            url = "redirect:/admin/companies/" + companyId + "/users?statusId=" + userDTO.getStatusId();
        }
        return url;
    }

    @GetMapping("{companyId}/departments")
    public String showDepartment(@PathVariable Long companyId,
                                 Principal principal,
                                 Model model) {
        String url = "admin/department/department.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        return url;
    }

    @GetMapping("{companyId}/departments/new")
    public String showDepartmentForm(@PathVariable Long companyId,
                                 Principal principal,
                                 Model model) {
        String url = "admin/department/department-create-form.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        return url;
    }

    @PostMapping("{companyId}/departments")
    public String insertDepartment(@PathVariable Long companyId,
                                   DepartmentDTO departmentDTO) {
        String url = "redirect:/admin/companies/" + companyId + "/departments";
        if(!adminService.createDepartment(departmentDTO)) {
            url = "redirect:/admin/companies/" + companyId + "/departments/form";
        }
        return url;
    }

    @GetMapping("{companyId}/departments/{departmentId}/edit")
    public String showDepartmentUpdate(@PathVariable Long companyId,
                                       @PathVariable Long departmentId,
                                     Principal principal,
                                     Model model) {
        String url = "admin/department/department-update-form.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        adminService.findDepartmentById(departmentId, model);
        return url;
    }

    @PutMapping("{companyId}/departments/{departmentId}")
    public String updateDepartment(DepartmentDTO departmentDTO) {
        String url = "redirect:/admin/companies/" + departmentDTO.getCompanyId() + "/departments/edit";
        if(adminService.updateDepartment(departmentDTO)) {
            url = "redirect:/admin/companies/" + departmentDTO.getCompanyId() + "/departments";
        }
        return url;
    }


    @GetMapping("{companyId}/profile")
    public String showCompanyProfile(@PathVariable Long companyId,
                                     Principal principal,
                                     Model model) {
        String url = "admin/profile/profile.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        adminService.findCompanyInfo(companyId, model);
        return url;
    }

    @GetMapping("{companyId}/profile/edit")
    public String showCompanyProfileForm(@PathVariable Long companyId,
                                     Principal principal,
                                     Model model) {
        String url = "admin/profile/profile-update-form.html";
        if(!urlService.findCompanyInfo(principal.getName(), companyId, model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        adminService.findCompanyInfo(companyId, model);
        return url;
    }

    @PutMapping("{companyId}/profile")
    public String updateCompanyInfo(@PathVariable Long companyId,
                                    CompanyDTO companyDTO) {
        String url = "redirect:/admin/companies/" + companyId + "/profile";
        if(!adminService.updateCompanyInfo(companyDTO)) {
            url = "redirect:/admin/companies/" +companyId;
        }
        return url;
    }
}
