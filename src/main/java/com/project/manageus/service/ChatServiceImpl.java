package com.project.manageus.service;

import com.project.manageus.repository.ChatJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatJPARepository chatJPA;

    public int count(){
        return (int)chatJPA.count();
    }

}
