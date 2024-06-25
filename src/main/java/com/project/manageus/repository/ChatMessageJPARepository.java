package com.project.manageus.repository;

import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface MappingMessageNum{
        Long id();
}

public interface ChatMessageJPARepository extends JpaRepository<ChatMessageEntity,Long> {
        public List<ChatMessageEntity> findByChatRoomIdAndIdGreaterThanEqual(Long roomId,Long minId);
        public void deleteByChatRoomId(Long roomId);
        public ChatMessageEntity findFirstByUserIdAndChatRoomIdOrderByIdAsc(Long userId, Long roomId);

}
