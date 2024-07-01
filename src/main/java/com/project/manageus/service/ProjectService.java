package com.project.manageus.service;


import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.dto.ProjectDetailDTO;
import com.project.manageus.dto.ProjectMemberDTO;
import com.project.manageus.entity.*;

import java.util.List;

public interface ProjectService {

    public List<ProjectDTO> projectList(Long id);
    public int projectMemberCount(Long projectId);
    public Long projectLeaderId(Long projectId);
    public List<ProjectMemberEntity> projectMemberList(Long projectId);
    public UserInfoEntity getUserInfo(Long userId);
    public int insertProjectDetail(ProjectDetailDTO projectDetailDTO);
    public List<ProjectDetailEntity> projectDetailList(Long projectId);
    public List<UserEntity> getCompanyUser(Long companyId);
    public ProjectEntity insertProject(ProjectDTO projectDTO);
    public void insertProjectMember(ProjectMemberDTO projectMemberDTO);
    public StatusEntity getStatus(Long statusId);
}
