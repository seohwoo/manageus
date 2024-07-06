package com.project.manageus.repository;

import com.project.manageus.entity.ProjectEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    public ProjectEntity findAllById(Long pId);

}
