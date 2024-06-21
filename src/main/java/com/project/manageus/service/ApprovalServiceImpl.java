package com.project.manageus.service;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
import com.project.manageus.repository.ApprovalJPARepository;
import com.project.manageus.repository.ApprovalTypeJPARepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    @Autowired
    public ApprovalServiceImpl(ApprovalJPARepository approvalJPA,
                               ApprovalTypeJPARepository approvalTypeJPA,
                               UserRepository userRepository) {

        this.approvalJPA = approvalJPA;
        this.approvalTypeJPA = approvalTypeJPA;
        this.userRepository = userRepository;
    }



    // 휴가 종류 가져오기
    @Override
    public void selectApprovalType(Model model) {
        List<ApprovalTypeEntity> approvalType = approvalTypeJPA.findAll();
        model.addAttribute("approvalType", approvalType);
    }

    @Override
    public void insertApproval(ApprovalDTO Adto) {

        // 오늘 날짜 구해오는 코드
        //LocalDateTime now = LocalDateTime.now();
        //Date signOff = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Long statusId = 1001L;
        Long Id = 10010003L;


        Date startDateUtil = Adto.getStartDate();

        // java.util.Date를 java.time.LocalDate로 변환
        LocalDate startDate = startDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // startDate에서 2일을 뺀 LocalDate 계산
        LocalDate signOffLocalDate = startDate.minusDays(2);

        // LocalDate를 java.util.Date로 변환
        Date signOff = Date.from(signOffLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());



        Adto.setId(Id);
        Adto.setUserId(Id);
        Adto.setStatusId(statusId);
        Adto.setSignOff(signOff);

        approvalJPA.save(Adto.toApprovalEntity());
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
