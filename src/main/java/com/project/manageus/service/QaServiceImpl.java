package com.project.manageus.service;

import com.project.manageus.dto.MailDTO;
import com.project.manageus.dto.QaDTO;
import com.project.manageus.entity.QaEntity;
import com.project.manageus.repository.QaJPARepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service
public class QaServiceImpl implements QaService{

    private final QaJPARepository qaJPA;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    public QaServiceImpl(QaJPARepository qaJPA,JavaMailSender emailSender,TemplateEngine templateEngine){
        this.qaJPA=qaJPA;
        this.emailSender=emailSender;
        this.templateEngine=templateEngine;
    }

    public int count(){
        return (int)qaJPA.count();

    }

    @Override
    public void qaWrite(QaDTO dto) {

        qaJPA.save(dto.toQaEntity());

    }

    @Override
    public void qaRead(Model model,int pageNum) {
        int pageSize=10;
        int count=qaJPA.countByRef(0L);
        List<QaDTO> list = Collections.emptyList();
        if(count>0) {
            Sort sort = Sort.by(Sort.Order.desc("reg"));
            Page<QaEntity> page = qaJPA.findByRef(0L, PageRequest.of(pageNum - 1, pageSize, sort));
            List<QaEntity> entityList = page.getContent();
            list=new ArrayList<>();
            for(QaEntity qe:entityList){
                QaDTO dto = qe.toQaDTO();
                list.add(dto);
            }
            model.addAttribute("list",list);
            model.addAttribute("count",count);
            model.addAttribute("pageNum",pageNum);
            model.addAttribute("pageSize",pageSize);
        }
        int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
        int startPage = (pageNum/10)*10+1;
        int pageBlock = 10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount){
            endPage = pageCount;
        }
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("startPage",startPage);
        model.addAttribute("pageBlock",pageBlock);
        model.addAttribute("endPage",endPage);

    }

    @Override
    public void qaContent(Model model, Long num) {
        int count = qaJPA.countByIdOrRef(num,num);
        List<QaDTO> list = Collections.emptyList();

        if(count>0){
            List<QaEntity> entitylist = qaJPA.findByIdOrRef(num,num);
            list = new ArrayList<>();
            for(QaEntity qe:entitylist) {
               list.add(qe.toQaDTO());
            }

            System.out.println("count : "+count+"list : " +list);
        }

        model.addAttribute("list",list);
        model.addAttribute("count",count);
        model.addAttribute("num",num);
    }

    @Override
    public void qaAnswer(String writer,Long ref, String content, int type) throws MessagingException {
                QaDTO dto = new QaDTO();
            if(type==1){
                content = "<직접 연락> "+ content;
            }else if(type==2){
                MailDTO mdto = new MailDTO();
                Optional<QaEntity> question =qaJPA.findById(ref);
                if(question.isPresent()){
                    mdto.setUser(question.get().getEmail());
                    mdto.setWriter("jaus0708@admin.com");
                    mdto.setTitle("문의글 답변입니다.");
                    mdto.setText(content);

                    sendMail(mdto);

                }
                content = "<온라인 답변> "+ content;
            }
                dto.setWriter(writer);
                dto.setRef(ref);
                dto.setContact("02-1234-1234");
                dto.setEmail("jaus0708@admin.com");
                dto.setContent(content);
                qaJPA.save(dto.toQaEntity());

    }

    @Override
    public void sendMail(MailDTO dto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8" );

//        HashMap<String,String> emailValues=new HashMap<>();
//        emailValues.put("title",dto.getTitle());
//        emailValues.put("text",dto.getText());
//        Context context = new Context();
//        emailValues.forEach((key,value)->{
//            context.setVariable(key,value);
//        });

//        String html = templateEngine.process("mail/mail",context);
        helper.setSubject(dto.getTitle());
        helper.setText(dto.getText());
        helper.setFrom(dto.getWriter());
        helper.setTo(dto.getUser());
        emailSender.send(message);
    }


}
