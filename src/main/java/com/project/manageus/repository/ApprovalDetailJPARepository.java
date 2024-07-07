package com.project.manageus.repository;

import com.project.manageus.entity.ApprovalDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApprovalDetailJPARepository extends JpaRepository<ApprovalDetailEntity, Long> {
    // 글번호에 맞는 정보 가져오기
    public List<ApprovalDetailEntity> findByApprovalId(Long approvalId);

    // 승인 업데이트
    Optional<ApprovalDetailEntity> findByApprovalIdAndUserId(Long approvalId, Long userId);


}
