package com.project.manageus.service;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
import com.project.manageus.repository.ApprovalJPARepository;
import com.project.manageus.repository.ApprovalTypeJPARepository;
import com.project.manageus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
