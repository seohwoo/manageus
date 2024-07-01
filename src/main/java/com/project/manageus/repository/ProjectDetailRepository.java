package com.project.manageus.repository;

import com.project.manageus.entity.ProjectDetailEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDetailRepository extends JpaRepository<ProjectDetailEntity, Long>{
    public List<ProjectDetailEntity> findAllByProjectId(Long projectId);
}
