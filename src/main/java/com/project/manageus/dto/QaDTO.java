package com.project.manageus.dto;

import com.project.manageus.entity.QaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QaDTO {
    private Long id;
    private String writer;
    private String email;
    private String contact;
    private String content;
    private Date reg;
    private int ref;

    @Builder
    public QaDTO(Long id,String writer,String email,String contact,String content,Date reg,int ref){
        this.id=id;
        this.writer=writer;
        this.email=email;
        this.contact=contact;
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
                .content(this.content)
                .reg(this.reg)
                .ref(this.ref).build();
    }
}
