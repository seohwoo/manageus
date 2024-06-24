package com.project.manageus.service;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.entity.ApprovalEntity;
import com.project.manageus.entity.ApprovalTypeEntity;
import com.project.manageus.entity.DepartmentEntity;
import com.project.manageus.entity.UserEntity;
import com.project.manageus.repository.ApprovalJPARepository;
import com.project.manageus.repository.ApprovalTypeJPARepository;
import com.project.manageus.repository.DepartmentRepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalJPARepository approvalJPA;
    private final ApprovalTypeJPARepository approvalTypeJPA;

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public ApprovalServiceImpl(ApprovalJPARepository approvalJPA,
                               ApprovalTypeJPARepository approvalTypeJPA,
                               UserRepository userRepository,
                               DepartmentRepository departmentRepository) {

        this.approvalJPA = approvalJPA;
        this.approvalTypeJPA = approvalTypeJPA;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    // 결재 리스트 가져오기
    @Override
    public void selectApprovalList(Model model, Long companyId) {
        List<ApprovalEntity> approvalList = approvalJPA.findByCompanyId(companyId);
        model.addAttribute("approvalList", approvalList);
    }

    // 결재 종류 가져오기
    @Override
    public void selectApprovalType(Model model) {
        List<ApprovalTypeEntity> approvalType = approvalTypeJPA.findAll();
        model.addAttribute("approvalType", approvalType);
    }

    // 결재 테이블 인서트
    @Override
    public void insertApproval(@PathVariable Long companyId,
                               @PathVariable Long id,
                               ApprovalDTO Adto) {


        Long statusId = 1001L;

        // 휴가 마감 날짜
        Date startDateUtil = Adto.getStartDate();
        LocalDate startDate = startDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate signOffLocalDate = startDate.minusDays(2);
        Date signOff = Date.from(signOffLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());



        Adto.setUserId(id);
        Adto.setStatusId(statusId);
        Adto.setSignOff(signOff);
        Adto.setCompanyId(companyId);

        approvalJPA.save(Adto.toApprovalEntity());
    }

    // 회사 부서 가져오기
    @Override
    public void selectDepartment(Long companyId, Model model) {
        List<DepartmentEntity> selectDepartment = departmentRepository.findAllByCompanyId(companyId);
        model.addAttribute("selectDepartment", selectDepartment);
    }

    // 회사 번호가 같은 정보 다 가져오기
    @Override
    public void selectCompanyId(Long companyId, Model model) {
        List<UserEntity> selectCompanyId = userRepository.findAllByCompanyId(companyId);
        model.addAttribute("selectCompanyId",selectCompanyId);
    }






    // ajax 연습
    @Override
    public void selectDepartment(Model model) {
        List<UserEntity> selectDepartment = userRepository.findAll();
        model.addAttribute("selectDepartment", selectDepartment);
    }

    @Override
    public void selectPeople(Long departmentId, Model model) {
        List<UserEntity> selectPeople = userRepository.findAllByDepartmentId(departmentId);
        model.addAttribute("selectPeople", selectPeople);
    }

//    @Autowired
//    private final ApprovalJPARepository service;
//    private ApprovalJPARepository ApprovalJPA;
/*
    @Override
    public void write() {
        // 실질적으로 구현할 코드 ( DB 연결 )
    }

    @Override
    public void selectApproval(String id, int pw, Model model) {
        System.out.println("id=" + id);
        System.out.println(("pw=" + pw));

        model.addAttribute("us", JPA.us(id, pw));
    }

    @Override
    public void isnertApproval(ApprovalDTO dto) {
        JPA.insertApproval(dto);
    }

 */
}
