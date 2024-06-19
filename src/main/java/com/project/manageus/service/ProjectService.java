package com.project.manageus.service;


import com.project.manageus.dto.ProjectDTO;
import com.project.manageus.entity.ProjectEntity;

import java.util.List;

public interface ProjectService {

    public List<ProjectDTO> projectList(Long id);

}
