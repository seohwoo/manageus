package com.project.manageus.service;

import com.project.manageus.dto.QaDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface QaService {
    public void qaWrite(QaDTO dto);
    public void qaRead(Model model,int pageNum);
    public void qaContent(Model model,Long num);
    public void qaAnswer(String writer,Long ref,String content,int type);
}
