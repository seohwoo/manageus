package com.project.manageus.repository;

import com.project.manageus.entity.QaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QaJPARepository extends JpaRepository<QaEntity,Long> {
    public List<QaEntity> findByIdOrRef(Long id,Long ref);
    public int countByRef(Long ref);
    public int countByIdOrRef(Long id,Long ref);
    public Page<QaEntity> findByRef(Long ref, Pageable pageable);

    @Query("select count(q) from QaEntity q where q.ref = 0 and q.id in (select qa.ref from QaEntity qa where qa.ref != 0)")
    public int answerCount();

    @Query("select count(q) from QaEntity q where q.ref = 0 and q.id not in (select qa.ref from QaEntity qa where qa.ref != 0)")
    public int nonAnswerCount();

    @Query("select q from QaEntity q where q.ref = 0 and q.id in (select qa.ref from QaEntity qa where qa.ref != 0)")
    public Page<QaEntity> answerList(Pageable pageable);

    @Query("select q from QaEntity q where q.ref = 0 and q.id not in (select qa.ref from QaEntity qa where qa.ref != 0)")
    public Page<QaEntity> nonAnswerList(Pageable pageable);



}
