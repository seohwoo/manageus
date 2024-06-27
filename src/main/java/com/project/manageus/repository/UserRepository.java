package com.project.manageus.repository;

import com.project.manageus.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public boolean existsByCompanyId(Long companyId);
    public List<UserEntity> findAllByCompanyId(Long companyId);
    public List<UserEntity> findAllByDepartmentId(Long departmentId);
    public List<UserEntity> findAllByDepartmentIdAndPositionId(Long departmentId, Long positionId);
    public List<UserEntity> findAllByDepartmentIdAndIdNotIn(Long departmentId,List<Long> ids);


}
