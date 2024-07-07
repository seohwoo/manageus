package com.project.manageus.repository;

import com.project.manageus.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

    public boolean existsByEmail(String email);

}
