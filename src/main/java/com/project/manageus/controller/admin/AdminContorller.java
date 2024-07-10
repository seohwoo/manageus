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
@RequestMapping("/admin/*")
public class AdminContorller {

    private final AdminService adminService;
    private final UrlService urlService;

    @GetMapping("companies/{companyId}")
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

    @GetMapping("users")
    public String showAllEmployee(@RequestParam Long statusId,
                                  Principal principal,
                                  Model model) {
        String url = "admin/employee/employee.html";
        Long companyId = Long.parseLong(principal.getName());
        if(!urlService.isValidCompany(principal.getName(), model)) {
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

    @PatchMapping("users/{id}")
    public String updateUserInfo(@PathVariable Long id,
                                 UserDTO userDTO) {
        String url = "redirect:/admin/users?statusId=" + adminService.findStatusIdById(id);
        if(adminService.updateUser(userDTO)) {
            url = "redirect:/admin/users?statusId=" + userDTO.getStatusId();
        }
        return url;
    }

    @GetMapping("departments")
    public String showDepartment(Principal principal,
                                 Model model) {
        String url = "admin/department/department.html";
        Long companyId = Long.parseLong(principal.getName());
        if(!urlService.isValidCompany(principal.getName(), model)) {
            url = "redirect:/admin/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        return url;
    }

    @GetMapping("departments/new")
    public String showDepartmentForm(Principal principal,
                                    Model model) {
        String url = "admin/department/department-create-form.html";
        Long companyId = Long.parseLong(principal.getName());
        if(!urlService.isValidCompany(principal.getName(), model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        return url;
    }

    @PostMapping("departments")
    public String insertDepartment(DepartmentDTO departmentDTO) {
        String url = "redirect:/admin/departments";
        if(!adminService.createDepartment(departmentDTO)) {
            url = "redirect:/admin/departments/form";
        }
        return url;
    }

    @GetMapping("departments/{departmentId}/edit")
    public String showDepartmentUpdate(@PathVariable Long departmentId,
                                       Principal principal,
                                       Model model) {
        String url = "admin/department/department-update-form.html";
        Long companyId = Long.parseLong(principal.getName());
        if(!urlService.isValidCompany(principal.getName(),model)) {
            url = "redirect:/admin/companies/" + principal.getName();
            return url;
        }
        adminService.findAllDepartment(companyId, model);
        adminService.findDepartmentById(departmentId, model);
        return url;
    }

    @PutMapping("departments/{departmentId}")
    public String updateDepartment(DepartmentDTO departmentDTO) {
        String url = "redirect:/admin/departments/edit";
        if(adminService.updateDepartment(departmentDTO)) {
            url = "redirect:/admin/departments";
        }
        return url;
    }


    @GetMapping("profile/companies/{companyId}")
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

    @GetMapping("profile/companies/{companyId}/edit")
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

    @PutMapping("profile/companies/{companyId}")
    public String updateCompanyInfo(@PathVariable Long companyId,
                                    CompanyDTO companyDTO) {
        String url = "redirect:/admin/profile/companies/" + companyId;
        if(!adminService.updateCompanyInfo(companyDTO)) {
            url = "redirect:/admin/companies/" +companyId;
        }
        return url;
    }
}
