package com.project.manageus.repository;

import com.project.manageus.entity.ApprovalEntity;
import jakarta.transaction.Transactional; // 쿼리문 직접 입력
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; // 쿼리문 직접 입력 ( 인서트는 또 다름
import org.springframework.data.jpa.repository.Query; // 쿼리문 직접 입력
import org.springframework.data.repository.query.Param; // 쿼리문 직접 입력

public interface ApprovalJPARepository extends JpaRepository<ApprovalEntity, Integer> {
    // 여기에 SQL 적으면됨
//    @Transactional //            ʼ
//    @Modifying //            ʼ
//    @Query(value="update freeboard set re_step=re_step+1 where ref=:ref and re_step > :re_step",nativeQuery = true)
//    public void writeUpdate(@Param("ref")int ref, @Param("re_step")int re_step);
}
