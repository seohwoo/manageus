package com.project.manageus.repository;


import com.project.manageus.dto.MessageDTO;
import com.project.manageus.entity.MessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageJPARepository extends JpaRepository<MessageEntity, Integer> {


    @Query(value = "SELECT * FROM alarm WHERE user_id = :userId",nativeQuery = true)
    List<MessageEntity> sendmessage (@Param("userId") int userId);  //보낸 메세지 전체 가져오기


    @Query(value = "SELECT * FROM alarm WHERE reader = :userId",nativeQuery = true)
    List<MessageEntity> getmessage (@Param("userId") int userId);   //받은 메세지 전체 가져오기

    @Transactional
    @Modifying
    @Query(value = "insert into alarm (user_id, subject, reader, read_type, read_date) values (:#{#messagedto.user_id}, :#{#messagedto.subject}, :#{#messagedto.reader}, :#{#messagedto.read_type}, :#{#messagedto.read_date})", nativeQuery = true)
    int messageing(@Param("messagedto") MessageDTO messagedto);  //메세지 전송 시 발생






}
