package com.project.manageus.controller.company;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.manageus.dto.AlarmDTO;
import com.project.manageus.dto.DepartmentDTO;
import com.project.manageus.service.AlarmService;
import com.project.manageus.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/companies/{companyId}/*")
public class AlarmController {

    private final AlarmService alarmService;
    private final UrlService urlService;


    public AlarmController(AlarmService alarmService, UrlService urlService) {
        this.alarmService = alarmService;
        this.urlService = urlService;
    }

    @GetMapping("/alarm/{id}/form")  // 쪽지 작성하는 곳
    public String write(Model model, Principal principal, @PathVariable Long companyId,
                        @PathVariable Long id ) {


        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.getdepartment(companyId, model );  // 회사아이디로 부서 찾기

        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);

        return "/company/alarm/write";
    }
    


    @PostMapping("/invitations/names")  //소속원 에이젝스용
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAlarmName(@RequestBody DepartmentDTO departmentDTO){
        System.out.println("=============did: " + departmentDTO.getId());
        System.out.println("=============cid: " + departmentDTO.getCompanyId());

       JsonObject names = alarmService.getAlarmNameDepartment(departmentDTO.getCompanyId(), departmentDTO.getId());

        Map<String, Object> result = new Gson().fromJson(names, Map.class);
        return  ResponseEntity.ok(result);
    }

    @PostMapping("/alarmnumber/number")  // 알림 에이젝스
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAlertCount(@RequestBody DepartmentDTO departmentDTO) { // 알림 카운트 로직

        System.out.println("=============didsdf: " + departmentDTO.getId());
        System.out.println("=============cidsdf: " + departmentDTO.getCompanyId());

        JsonObject names = alarmService.getAlarmCount(departmentDTO.getId());

        Map<String, Object> response = new Gson().fromJson(names, Map.class);

        return ResponseEntity.ok(response);
    }



    // 포스트 매핑 처리해야됨

    @PostMapping("/alarm/{id}/formpro")
    public String writepro(Principal principal, AlarmDTO alarmDTO,@PathVariable Long companyId,
                           @PathVariable Long id,@RequestParam("reader") String reader,
                           @RequestParam("subject") String subject){

        Long readtype = Long.valueOf(reader);   //append로  스크립트 처리 < -- 여려명을 리스트로 받아라

        Long userId = Long.parseLong(principal.getName());
         Long readers = Long.parseLong(reader);


        alarmDTO.setUserId(userId);
        alarmDTO.setReader(readers);
        alarmDTO.setSubject(subject);
        alarmDTO.setReadType(readtype);

        alarmService.insert(alarmDTO);

        return  "redirect:/companies/" + companyId + "/alarm/" + id + "/spend";
    }





    // 아래는 내가 받은 쪽지 내역
    @GetMapping("/alarm/{id}/receive")  // 받은 쪽지
    public String receive(Model model, Principal principal, @PathVariable Long companyId,
                          @PathVariable Long id) {

        Long userId = Long.parseLong(principal.getName()); // 세션 받아오기

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.receive(userId, model);

        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);

        return "/company/alarm/receive";
    }






    //받은내용 읽기

    @GetMapping("/alarm/{id}/readreceive/{messageId}") //받은쪽지 읽기
    public String readreceive(Model model, Principal principal, @PathVariable Long companyId,
                              @PathVariable Long id, @PathVariable Long messageId){

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.readcount(messageId);
        alarmService.readreceive(messageId, model);

        return "/company/alarm/readreceive";
    }


    // 아래는 내가 보낸 쪽지 내역
    @GetMapping("/alarm/{id}/spend") // 내가 보낸 쪽지 내역
    public String spend(Model model, Principal principal, @PathVariable Long companyId,
                        @PathVariable Long id) {

        Long userId = Long.parseLong(principal.getName()); // 세션 받아오기

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.spendalarm(userId, model); // 보낸 내역 전체 가져오기

        return "/company/alarm/spend";
    }

    @GetMapping("/alarm/{id}/readspend/{messageId}")  // 보낸내역 상세 보기
    public String readspend(Model model, Principal principal, @PathVariable Long companyId,
                            @PathVariable Long id, @PathVariable Long messageId){
        
        Long userId = Long.parseLong(principal.getName());

        if (!urlService.findUserInfo(principal.getName(), companyId, model)
                || id != Long.parseLong(principal.getName())) {

            return "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
        }

        alarmService.readspend(messageId, model);

        return "/company/alarm/readspend";
    }
}
