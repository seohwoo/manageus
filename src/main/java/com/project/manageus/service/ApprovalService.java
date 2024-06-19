package com.project.manageus.service;

import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

public interface ApprovalService {

    // 휴가 종류 가져오기
    public void selectApprovalType(Model model);

    // 휴가 테이블 인서트
    public void insertApproval(ApprovalDTO Adto);

    /*
    public void write();

    public void selectApproval(String id, int pw, Model model);

    public void isnertApproval(ApprovalDTO dto);
    */
}

