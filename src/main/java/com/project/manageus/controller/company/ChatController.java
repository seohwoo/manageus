package com.project.manageus.controller.company;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatMessageDTO;
import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.service.ChatService;
import com.project.manageus.service.UrlService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/companies/*")
public class ChatController {

    private final ChatService service;
    private final UrlService urlService;

    @Autowired
    public ChatController(ChatService service,UrlService urlService){
        this.service=service;
        this.urlService=urlService;
    }
    @GetMapping("/{companyId}/chat/{roomId}/{id}")
    public String chatting(Model model, Principal principal,@PathVariable(value = "id") Long id,@PathVariable(value = "roomId")Long roomId,@PathVariable(value="companyId")Long companyId){
        String url ;
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
             url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        service.enterChatRoom(model,id,roomId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);
        return "/company/chat/chater";
    }
    @GetMapping("/{companyId}/chat")
    public String chatRoomList(Principal principal,@PathVariable(value="companyId")Long companyId,Model model){
        String url ;
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        String sid = (String)principal.getName();
        Long id = Long.parseLong(sid);
        service.chatList(model,id);
        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);
        return "/company/chat/chatRoomList";
    }
    @PostMapping("/{companyId}/chat")
    public String chatRoomCreate(Principal principal,@PathVariable(value="companyId")Long companyId,ChatRoomDTO dto,Model model){
        String url ;
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
       String id =(String)principal.getName();
        Long idl = Long.parseLong(id);
        Long roomId=service.chatNewRoom(dto,idl);
        url="redirect:/companies/"+companyId+"/chat/"+roomId+"/"+idl;
        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);
        return url;
    }
    @DeleteMapping("/{companyId}/chat")
    public String chatRoomExit(Principal principal,@PathVariable(value="companyId")Long companyId,ChatDTO dto, Model model){
        String url ="redirect:/companies/"+companyId+"/chat";
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companies/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        service.chatExit(dto);
        return url;
    }
    @PostMapping("/send-message")
    @ResponseBody
    public ResponseEntity<String> sendMessage(@RequestBody ChatMessageDTO message) {
        // 메시지 처리 (예: 다른 사용자에게 방송, 데이터베이스에 저장 등)
        service.sendMessage(message);
        // 여기에서 메시지를 데이터베이스에 저장하거나, 다른 클라이언트에 방송하는 등의 로직을 추가할 수 있습니다.
        return ResponseEntity.ok("Message received");
    }
    @GetMapping("{companyId}/invitations/{roomId}")
    public String invitations(Model model,@PathVariable(value="companyId")Long companyId,@PathVariable Long roomId){
            service.chatInvitations(model,companyId);
            model.addAttribute("roomId",roomId);
        return "company/chat/chatInvitation";
    }
    @PostMapping("{companyId}/invitations")
    public String insertInvitations(@PathVariable(value="companyId")Long companyId,@RequestParam(value = "personId")Long personId,@RequestParam(value = "roomId")Long roomId){
        ChatDTO dto = new ChatDTO();
        dto.setUserId(personId);
        dto.setChatRoomId(roomId);
        service.userInvitation(dto);
        String url="redirect:/companies/"+companyId+"/chatRoomList";



        return url;
    }
    @PostMapping("/invitations/names")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getInvitationsNames(@RequestBody ChatRoomDTO dto) {
        JsonObject names = service.getNamesfromDepartment(dto);
        // JsonObject를 Map으로 변환
        Map<String, Object> result = new Gson().fromJson(names, Map.class);

        return ResponseEntity.ok(result);
    }


}
