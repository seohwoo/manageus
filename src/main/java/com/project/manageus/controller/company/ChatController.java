package com.project.manageus.controller.company;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.manageus.dto.ChatCheckDTO;
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
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/manageus/*")
public class ChatController {

    private final ChatService service;
    private final UrlService urlService;

    @Autowired
    public ChatController(ChatService service,UrlService urlService){
        this.service=service;
        this.urlService=urlService;
    }

    //채팅방 목록
    @GetMapping("/chat")
    public String chatRoomList(Principal principal,Model model){
        Long companyId = Long.parseLong(urlService.findCompanyUrl(principal.getName()));
        String url ;
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:/manageus/" + companyId;
            return url;
        }
        String sid = (String)principal.getName();
        Long id = Long.parseLong(sid);
        service.chatList(model,id);
        service.chatInvitations(model,companyId);
        model.addAttribute("companyId",companyId);
        model.addAttribute("id",id);
        return "company/chat/chatRoomList";
    }

    //채팅방들어가기
    @GetMapping("/chat/room/{roomId}")
    public String chatting(Model model, Principal principal,@PathVariable(value = "roomId")Long roomId){
        String url ;
        Long id = Long.parseLong(principal.getName());
        if(!urlService.isValidUser(principal.getName(), model)) {
             url = "redirect:/manageus/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
        service.enterChatRoom(model,id,roomId);
        model.addAttribute("id",id);
        return "company/chat/chater";
    }
    //채팅 방만들기
    @PostMapping("/chat/room/new")
    public String chatRoomCreate(Principal principal,ChatRoomDTO dto,Model model){
        String url ;
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:/manageus/" + urlService.findCompanyUrl(principal.getName());
            return url;
        }
       String id =(String)principal.getName();
        Long idl = Long.parseLong(id);
        Long roomId=service.chatNewRoom(dto,idl);

        url="redirect:/manageus/chat";
        model.addAttribute("id",id);
        return url;
    }
    //채팅방 이름 수정
    @PostMapping("/chat/room/{roomId}/edit")
    public String roomEdit(@PathVariable(value="roomId")Long roomId ,@RequestParam(value = "newRoomName") String newRoomName){
        String url="redirect:/manageus/chat";
        service.changRoomName(roomId,newRoomName);
        return url;
    }
    //채팅방 초대
    @PostMapping("/chat/room/{roomId}/invitations")
    public String insertInvitations(@RequestParam(value = "personId")Long personId,@PathVariable Long roomId){
        ChatDTO dto = new ChatDTO();
        dto.setUserId(personId);
        dto.setChatRoomId(roomId);
        service.userInvitation(dto);
        String url="redirect:/manageus/chat";
        return url;
    }
    //채팅방 나가기
    @DeleteMapping("/chat")
    public String chatRoomExit(Principal principal,ChatDTO dto, Model model){
        String url ="redirect:/manageus/chat";
        if(!urlService.isValidUser(principal.getName(), model)) {
            url = "redirect:/manageus/" + urlService.findCompanyUrl(principal.getName());
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

    @PostMapping("/invitations/names")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getInvitationsNames(@RequestBody ChatRoomDTO dto) {
        JsonObject names = service.getNamesfromDepartment(dto);
        // JsonObject를 Map으로 변환
        Map<String, Object> result = new Gson().fromJson(names, Map.class);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkTime")
    @ResponseBody
    public ResponseEntity<String> checkTime(@RequestBody ChatDTO dto) {
        service.checkLastTime(dto);
        // 여기에서 메시지를 데이터베이스에 저장하거나, 다른 클라이언트에 방송하는 등의 로직을 추가할 수 있습니다.
        return ResponseEntity.ok("Message received");
    }
    @RequestMapping("/chatAlarm")
    public @ResponseBody List<ChatCheckDTO> chatAlarm(Principal principal){
        Long id = Long.parseLong(principal.getName());
        return service.chatAlarm(id);
    }
}
