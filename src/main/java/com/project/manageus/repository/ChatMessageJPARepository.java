package com.project.manageus.repository;

import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface ChatMessageJPARepository extends JpaRepository<ChatMessageEntity,Long> {
        public List<ChatMessageEntity> findByChatRoomIdAndIdGreaterThanEqual(Long roomId,Long minId);
        @Transactional
        public void deleteByChatRoomId(Long roomId);
        public ChatMessageEntity findFirstByUserIdAndChatRoomIdOrderByIdAsc(Long userId, Long roomId);

}
