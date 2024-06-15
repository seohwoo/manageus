package com.project.manageus.repository;

import com.project.manageus.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long> {
}
