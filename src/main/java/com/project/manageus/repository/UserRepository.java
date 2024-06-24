package com.project.manageus.repository;

import com.project.manageus.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public boolean existsByCompanyId(Long companyId);
    public List<UserEntity> findAllByCompanyId(Long companyId);
    public List<UserEntity> findAllByDepartmentId(Long departmentId);
    public List<UserEntity> findAllByDepartmentIdAndPositionId(Long departmentId, Long positionId);
    public List<UserEntity> findByDepartmentId(Long departmentId); // 부서속 사람 찾기

    public List<UserEntity> findAllByCompanyIdAndDepartmentId(Long companyId,Long departmentId);

}
