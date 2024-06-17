package com.project.manageus.service;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.repository.ApprovalJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ApprovalServiceImpl implements ApprovalService {
    // 배워

    private final ApprovalJPARepository JPA;

    @Autowired
    public ApprovalServiceImpl(ApprovalJPARepository JPA) {
        this.JPA = JPA;
    }
    // 배워

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
