package com.project.manageus.service;

import com.project.manageus.repository.ApprovalDetailJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalDetailServiceImpl implements ApprovalDetailService {

    @Autowired
    private ApprovalDetailJPARepository ApprovalDetailJPA;
}
