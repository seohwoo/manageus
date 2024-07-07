package com.project.manageus.repository;

import com.project.manageus.entity.ChatEntity;
import com.project.manageus.entity.ChatIDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ChatJPARepository extends JpaRepository<ChatEntity, ChatIDEntity> {
    public List<ChatEntity> findByUserId(Long userId);

    public List<ChatEntity> findByChatRoomId(Long roomId);

    @Transactional
    public void deleteByUserIdAndChatRoomId(Long userId, Long chatRoomId);

    public int countByChatRoomId(Long chatRoomId);

    public int countByUserIdAndChatRoomId(Long userId, Long chatRoomId);






}