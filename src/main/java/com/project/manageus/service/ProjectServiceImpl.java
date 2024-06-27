package com.project.manageus.service;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.dto.ProjectDetailDTO;
import com.project.manageus.entity.ProjectDetailEntity;
import com.project.manageus.entity.ProjectEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import com.project.manageus.entity.UserInfoEntity;
import com.project.manageus.repository.ProjectDetailRepository;
import com.project.manageus.repository.ProjectMemberRepository;
import com.project.manageus.repository.ProjectRepository;
import com.project.manageus.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectDetailRepository projectDetailRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository
                                ,ProjectMemberRepository projectMemberRepository
                                ,ProjectDetailRepository projectDetailRepository
                                ,UserInfoRepository userInfoRepository){
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectDetailRepository = projectDetailRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public List<ProjectDTO> projectList(Long id) {
        List<ProjectMemberEntity> myProjectIdList = projectMemberRepository.findAllByUserId(id);
        List<ProjectEntity> myProjectEntityList = new ArrayList<>();
        for(ProjectMemberEntity s : myProjectIdList){
            Long pId = s.getProjectId();
            System.out.println(pId);
            ProjectEntity pe = projectRepository.findAllById(pId);
            myProjectEntityList.add(pe);
        }
        List<ProjectDTO> myProjectDTOList = new ArrayList<>();

        for(ProjectEntity s : myProjectEntityList){
            ProjectDTO pDTO = s.toProjectDTO();
            myProjectDTOList.add(pDTO);
        }



        return myProjectDTOList;
    }

    @Override
    public int projectMemberCount(Long projectId) {
        int count = projectMemberRepository.countByProjectId(projectId);
        return count;
    }

    @Override
    public Long projectLeaderId(Long projectId) {
        Long leaderId = projectRepository.findAllById(projectId).getUserId();
        return leaderId;
    }

    @Override
    public List<ProjectMemberEntity> projectMemberList(Long projectId) {
        List<ProjectMemberEntity> projectMemberEntityList = projectMemberRepository.findAllByProjectId(projectId);

        return projectMemberEntityList;
    }

    @Override
    public UserInfoEntity getUserInfo(Long userId) {
        Optional<UserInfoEntity> userInfo = userInfoRepository.findById(userId);
        return userInfo.orElse(null);
    }

    @Override
    public int insertProjectDetail(ProjectDetailDTO projectDetailDTO) {
        ProjectDetailEntity projectDetailEntity = projectDetailDTO.toProjectDetailEntity();
        ProjectDetailEntity result = projectDetailRepository.save(projectDetailEntity);
        return result != null ? 1 : 0;

    }

    @Override
    public List<ProjectDetailEntity> projectDetailList(Long projectId) {
        List<ProjectDetailEntity> projectDetailEntityList = projectDetailRepository.findAllByProjectId(projectId);

        return projectDetailEntityList;
    }
}
