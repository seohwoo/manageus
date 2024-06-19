package com.project.manageus.service;

import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.entity.ProjectEntity;
import com.project.manageus.entity.ProjectMemberEntity;
import com.project.manageus.repository.ProjectMemberRepository;
import com.project.manageus.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository
                              , ProjectMemberRepository projectMemberRepository){
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
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
}
