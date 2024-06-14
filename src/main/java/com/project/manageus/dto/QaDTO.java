package com.project.manageus.dto;

import com.project.manageus.entity.QaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QaDTO {

    private int id;
    private String writer;
    private String email;
    private String contact;
    private String password;
    private String content;
    private Date reg;
    private int ref;

    @Builder
    public QaDTO(int id,String writer,String email,String contact,String password,String content,Date reg,int ref){
        this.id=id;
        this.writer=writer;
        this.email=email;
        this.contact=contact;
        this.password=password;
        this.content=content;
        this.reg=reg;
        this.ref=ref;

    }
    public QaEntity toQaEntity(){
        return QaEntity.builder()
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
