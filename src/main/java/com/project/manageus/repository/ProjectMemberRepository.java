package com.project.manageus.repository;

import com.project.manageus.entity.ProjectEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, Long> {
    public List<ProjectMemberEntity> findAllByUserId(Long id);
    public int countByProjectId(Long projectId);
    public List<ProjectMemberEntity> findAllByProjectId(Long projectId);
}
