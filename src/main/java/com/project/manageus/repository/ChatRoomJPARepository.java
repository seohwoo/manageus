package com.project.manageus.repository;

import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ChatRoomJPARepository extends JpaRepository<ChatRoomEntity,Integer> {

}

