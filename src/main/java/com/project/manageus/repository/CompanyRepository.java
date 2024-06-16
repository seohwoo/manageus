package com.project.manageus.repository;

import com.project.manageus.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    public CompanyEntity findByInviteCode(String inviteCode);
    public boolean existsByInviteCode(String inviteCode);
}
