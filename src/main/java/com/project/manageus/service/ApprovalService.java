package com.project.manageus.service;

import com.google.gson.JsonObject;
import com.project.manageus.dto.ApprovalDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.entity.ApprovalTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ApprovalService {

    // 결재 리스트 가져오기
    public void selectApprovalList(Model model, Long companyId);

    // 결재 종류 가져오기
    public void selectApprovalType(Model model);

    // 결재 테이블 인서트
    public void insertApproval(@PathVariable Long companyId,
                               @PathVariable Long id,
                               ApprovalDTO Adto, List<Long> personId);

    // 회사 부서 가져오기
    public void selectDepartment(Long companyId, Model model);

    // 부서에 맞는 사람 가져오기
    public JsonObject selectPositionPeople(DepartmentDTO dto, Long userId);

    // 글번호에 맞는 정보 가져오기
    public void selectApprovalDetail(Long approvalId, Long id, Model model);

    // 승인완료 업데이트
    public void approvalUpdate(Long approvalId, Long userId);

    // 결재반려 업데이트
    public void approvalReject(Long approvalId, Long status);

    public Long findUserByAprovalId(Long approvalId);


    // 회사 번호가 같은 정보 다 가져오기
//    public void selectCompanyId(Long companyId, Model model);






/*
    // ajax 연습
    public void selectDepartment(Model model);

    public void selectPeople(Long departmentId, Model model);
*/
    /*
    public void write();

    public void selectApproval(String id, int pw, Model model);

    public void isnertApproval(ApprovalDTO dto);
    */
}

