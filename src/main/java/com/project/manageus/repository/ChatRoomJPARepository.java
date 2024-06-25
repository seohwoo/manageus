package com.project.manageus.repository;


import com.project.manageus.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatRoomJPARepository extends JpaRepository<ChatRoomEntity, Long> {
    public List<ChatRoomEntity> findByIdInOrderByRegDesc(List<Long> chatRoomIds);
    public void deleteById(Long roomId);
}

