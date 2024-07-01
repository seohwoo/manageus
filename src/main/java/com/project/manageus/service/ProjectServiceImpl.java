package com.project.manageus.service;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.dto.ProjectDetailDTO;
import com.project.manageus.dto.ProjectMemberDTO;
import com.project.manageus.entity.*;
import com.project.manageus.repository.*;
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
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final StatusRepository statusRepository;


    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository
                                , ProjectMemberRepository projectMemberRepository
                                , ProjectDetailRepository projectDetailRepository
                                , UserRepository userRepository
                                , UserInfoRepository userInfoRepository
                                , StatusRepository statusRepository){
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectDetailRepository = projectDetailRepository;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.statusRepository = statusRepository;
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

    @Override
    public List<UserEntity> getCompanyUser(Long companyId) {
        List<UserEntity> userEntityList = userRepository.findAllByCompanyId(companyId);
        return userEntityList;
    }

    @Override
    public ProjectEntity insertProject(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = projectDTO.toProjectEntity();
        projectEntity = projectRepository.save(projectEntity);
        return projectEntity;
    }

    @Override
    public void insertProjectMember(ProjectMemberDTO projectMemberDTO) {
        ProjectMemberEntity projectMemberEntity = projectMemberDTO.toProjectMemberEntity();
        projectMemberRepository.save(projectMemberEntity);
    }

    @Override
    public StatusEntity getStatus(Long statusId) {
        Optional<StatusEntity> status = statusRepository.findById(statusId);
        return status.orElse(null);
    }
}
