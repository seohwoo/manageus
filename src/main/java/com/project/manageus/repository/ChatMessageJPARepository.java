package com.project.manageus.repository;

import com.project.manageus.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageJPARepository extends JpaRepository<ChatMessageEntity,Long> {
        public List<ChatMessageEntity> findByChatRoomId(Long roomId);
        public void deleteByChatRoomId(Long roomId);
}
