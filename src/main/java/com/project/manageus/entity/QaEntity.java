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
    private int id;
    private String writer;
    private String email;
    private String contact;
    private String password;
    private String content;
    @CreationTimestamp
    @Column(name="reg")
    private Date reg;
    private int ref;

    @Builder
    public QaEntity(int id,String writer,String email,String contact,String password,String content,Date reg,int ref){
        super();
        this.id=id;
        this.writer=writer;
        this.email=email;
        this.contact=contact;
        this.password=password;
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
                .password(this.password)
                .content(this.content)
                .reg(this.reg)
                .ref(this.ref).build();
    }
}
