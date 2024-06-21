package com.project.manageus.repository;

import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatIDEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatJPARepository extends JpaRepository<ChatEntity, ChatIDEntity> {
    public List<ChatEntity> findByUserId(Long userId);

    public ChatEntity findByChatRoomId(Long roomId);

    public void deleteByUserIdAndChatRoomId(Long userId, Long chatRoomId);

    public int countByChatRoomId(Long chatRoomId);

    public int countByUserIdAndChatRoomId(Long userId, Long chatRoomId);


}