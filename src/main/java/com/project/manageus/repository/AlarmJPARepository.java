package com.project.manageus.repository;

import com.project.manageus.entity.AlarmEntity;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmJPARepository extends JpaRepository<AlarmEntity, Long> {

    List<AlarmEntity> findByUserId(Long userId, Sort sort); //보낸 전체메세지

    List<AlarmEntity> findByReader(Long userId, Sort sort); //받은 전체메세지


}
