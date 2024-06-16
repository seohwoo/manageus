package com.project.manageus.entity;

import com.project.manageus.dto.QaDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="qa")
@DynamicInsert
public class QaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String email;
    private String contact;
    private String content;
    @CreationTimestamp
    @Column(name="reg")
    private Date reg;
    private Long ref;

    @Builder
    public QaEntity(Long id,String writer,String email,String contact,String content,Date reg,Long ref){
        super();
        this.id=id;
        this.writer=writer;
        this.email=email;
        this.contact=contact;
        this.content=content;
        this.reg=reg;
        this.ref=ref;

    }
    public QaDTO toQaDTO(){
        return QaDTO.builder()
                .id(this.id)
                .writer(this.writer)
                .email(this.email)
                .contact(this.contact)
                .content(this.content)
                .reg(this.reg)
                .ref(this.ref).build();
    }
}
