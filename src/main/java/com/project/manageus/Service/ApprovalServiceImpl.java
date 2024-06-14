package com.project.manageus.Service;

import com.project.manageus.repository.ApprovalJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl  implements ApprovalService {

    @Autowired
    private ApprovalJPARepository mapper;

    // @Override
    // 예시
    // public void myproduct(Model model,String username) {
    // }
}
