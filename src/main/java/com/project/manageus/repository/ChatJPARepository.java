package com.project.manageus.repository;


import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatIDEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJPARepository extends JpaRepository<ChatEntity, ChatIDEntity> {
}
