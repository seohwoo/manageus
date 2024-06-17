package com.project.manageus.repository;


import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatJPARepository extends JpaRepository<ChatEntity, ChatID> {
    public List<Long> findByUserId(Long id);
}
