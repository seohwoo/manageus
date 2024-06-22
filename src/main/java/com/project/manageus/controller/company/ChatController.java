package com.project.manageus.controller.company;

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

@Controller
@Log4j2
@RequestMapping("/companis/*")
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
             url = "redirect:/company/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        service.enterChatRoom(model,id,roomId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);
        return "/company/chat/chater";
    }
    @GetMapping("/{companyId}/chatRoomList")
    public String chatRoomList(Principal principal,@PathVariable(value="companyId")Long companyId,Model model){
        String url ;
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companis/" + urlService.findCompanyUrl(principal.getName());
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
            url = "redirect:/companis/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        System.out.println("채팅방 생성!!");
       String id =(String)principal.getName();
        Long idl = Long.parseLong(id);
        System.out.println("id===="+idl);
        Long roomId=service.chatNewRoom(dto,idl);
        url="redirect:/companis/{companyId}/chat/"+idl+"/"+roomId;
        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);
        return url;
    }
    @DeleteMapping("/{companyId}/chat")
    public String chatRoomExit(Principal principal,@PathVariable(value="companyId")Long companyId,ChatDTO dto,Model model){
        String url ;
        if(!urlService.findUserInfo(principal.getName(), companyId, model)) {
            url = "redirect:/companis/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        service.chatExit(dto);
        return "redirect:/companis/chat/chatRoomList";
    }
    @PostMapping("/send-message")
    @ResponseBody
    public ResponseEntity<String> sendMessage(@RequestBody ChatMessageDTO message) {
        // 메시지 처리 (예: 다른 사용자에게 방송, 데이터베이스에 저장 등)
        System.out.println("Received message from " + message.getUserId() + ": " + message.getMessage());
        System.out.println("ChatRoom ID: " + message.getChatRoomId());
        service.sendMessage(message);
        // 여기에서 메시지를 데이터베이스에 저장하거나, 다른 클라이언트에 방송하는 등의 로직을 추가할 수 있습니다.
        return ResponseEntity.ok("Message received");
    }
    @GetMapping("{companyId}/invitations")
    public @ResponseBody String invitations(Model model,@PathVariable(value="companyId")Long companyId){

        return "company/chat/chatInvitation";
    }
    @PostMapping("{companyId}/invitations")
    public String insertInvitations(@PathVariable(value="companyId")Long companyId
                                    ,@RequestParam(value = "userId")Long userId){
        String url="redirect:/"+companyId+"/chatRoomList";
        return url;
    }
}
