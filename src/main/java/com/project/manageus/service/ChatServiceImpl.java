package com.project.manageus.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.manageus.dto.ChatCheckDTO;
import com.project.manageus.dto.ChatDTO;
import com.project.manageus.dto.ChatMessageDTO;
import com.project.manageus.dto.ChatRoomDTO;
import com.project.manageus.entity.*;
import com.project.manageus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {


    private final ChatJPARepository chatJPA;
    private final ChatRoomJPARepository chatRoomJPA;
    private final ChatMessageJPARepository chatMessageJPA;
    private final DepartmentRepository departmentJPA;
    private final UserRepository userJPA;




    @Autowired
    public ChatServiceImpl(ChatJPARepository chatJPA,ChatRoomJPARepository chatRoomJPA,ChatMessageJPARepository chatMessageJPA,DepartmentRepository departmentJPA,UserRepository userJPA){
        this.chatJPA=chatJPA;
        this.chatRoomJPA=chatRoomJPA;
        this.chatMessageJPA=chatMessageJPA;
        this.departmentJPA = departmentJPA;
        this.userJPA=userJPA;

    }

    public int count(){
        return (int)chatJPA.count();
    }
    @Override
    public Long chatNewRoom(ChatRoomDTO dto,Long id){
        Optional<UserEntity> optional = userJPA.findById(id);
        String username=null;
        if(optional.isPresent()){
            UserEntity userEntity=optional.get();
            dto.setName(userEntity.getUserInfo().getName());
            username=userEntity.getUserInfo().getName();
        }
        ChatDTO cdto = new ChatDTO();
        ChatMessageDTO mdto = new ChatMessageDTO();
        chatRoomJPA.save(dto.toChatRoomEntity());
        List<ChatRoomEntity> list= chatRoomJPA.findAll();
        ChatRoomEntity max = Collections.max(list, Comparator.comparingLong(ChatRoomEntity::getId));
        Long nextid=max.getId();
        mdto.setChatRoomId(nextid);
        cdto.setChatRoomId(nextid);
        cdto.setUserId(id);
        mdto.setUserId(id);
        chatJPA.save(cdto.toChatEntity());
        mdto.setMessage("님 입장하셧습니다.");
        chatMessageJPA.save(mdto.toChatMessageEntity());
        return nextid;

    }

    @Override
    public void chatList(Model model, Long id) {
        List<ChatEntity> roomIdList = chatJPA.findByUserId(id);
        ArrayList<Long> roomIds = new ArrayList<>();
        for(ChatEntity ce:roomIdList){
           ChatDTO dto = ce.toChatDTO();
            roomIds.add(dto.getChatRoomId());
        }
        List<ChatRoomEntity> list = chatRoomJPA.findByIdInOrderByRegDesc(roomIds);
        model.addAttribute("list",list);
        model.addAttribute("id",id);
    }



    @Override
    public void enterChatRoom(Model model, Long id, Long roomId) {
        ChatDTO dto = new ChatDTO();
        dto.setUserId(id);
        dto.setChatRoomId(roomId);
        chatJPA.save(dto.toChatEntity());
        List list = Collections.emptyList();
        Optional<UserEntity> ous = userJPA.findById(id);
        String nickname = null;
        if (ous.isPresent()) {
            nickname = ous.get().getUserInfo().getName();
            model.addAttribute("nickname", nickname);
        }

        Long minId = 0l;
        minId=chatMessageJPA.findFirstByUserIdAndChatRoomIdOrderByIdAsc(id, roomId).getId();
        list = chatMessageJPA.findByChatRoomIdAndIdGreaterThanEqual(roomId, minId);
        int count = chatJPA.countByUserIdAndChatRoomId(id, roomId);
        List<ChatMessageEntity> cmlist = Collections.emptyList();
        if (count != 0) {
            ChatMessageDTO mdto = new ChatMessageDTO();
            // cmlist = chatMessageJPA.findByChatRoomId(roomId);
            model.addAttribute("cmlist", list);
            model.addAttribute("roomId", roomId);
        }
    }

    @Override
    public void chatExit(ChatDTO dto) {
        int count = chatJPA.countByChatRoomId(dto.getChatRoomId());
        if(count>1){
        chatJPA.deleteByUserIdAndChatRoomId(dto.getUserId(),dto.getChatRoomId());
        } else if (count==1) {
            chatMessageJPA.deleteByChatRoomId(dto.getChatRoomId());
            chatRoomJPA.deleteById(dto.getChatRoomId());
            chatJPA.deleteByUserIdAndChatRoomId(dto.getUserId(),dto.getChatRoomId());


        }
    }

    @Override
    public void sendMessage(ChatMessageDTO dto) {
        chatMessageJPA.save(dto.toChatMessageEntity());
    }

    @Override
    public void chatInvitations(Model model, Long companyId) {
        List<DepartmentEntity> departments = departmentJPA.findByCompanyId(companyId);
        model.addAttribute("departments",departments);
    }

    @Override
    public JsonObject getNamesfromDepartment(ChatRoomDTO dto ) {
        JsonObject jsonObject = new JsonObject();
        List<ChatEntity> chatlist = chatJPA.findByChatRoomId(dto.getId());
        List<Long> list = new ArrayList<>();
        for(ChatEntity ce:chatlist){
            list.add(ce.getUserId());
        }
        List<UserEntity> users = userJPA.findAllByDepartmentIdAndIdNotIn(dto.getDepartmentId(),list);
        JsonArray jsonArray = new JsonArray();
        for (UserEntity ue : users) {
            JsonObject jsonObj = new JsonObject();
            String user = ue.getUserInfo().getName() + " " + ue.getPosition().getName();
            Long id = ue.getId();

            jsonObj.addProperty("userId", id);
            jsonObj.addProperty("fullName", user);
            jsonArray.add(jsonObj);
        }
        jsonObject.add("DepartmentMember", jsonArray);
        return jsonObject;
    }

    @Override
    public void userInvitation(ChatDTO dto) {
        ChatIDEntity chatId= new ChatIDEntity(dto.getUserId(),dto.getChatRoomId());
        Optional<ChatEntity> room =chatJPA.findById(chatId);
        if(room.isEmpty()){
            chatJPA.save(dto.toChatEntity());
           Optional<UserEntity> user= userJPA.findById(dto.getUserId());
            ChatMessageDTO mdto = new ChatMessageDTO();
            mdto.setChatRoomId(dto.getChatRoomId());
            mdto.setUserId(dto.getUserId());
            mdto.setMessage("님 입장하셧습니다.");
            chatMessageJPA.save(mdto.toChatMessageEntity());
        }

    }

    @Override
    public void changRoomName(Long roomId, String newRoomName) {

        Optional<ChatRoomEntity> room =chatRoomJPA.findById(roomId);
        if(room.isPresent()){
            ChatRoomDTO dto = room.get().toChatRoomDTO();

            System.out.println("==============="+dto);
            dto.setName(newRoomName);
            System.out.println("==============="+dto);
            chatRoomJPA.save(dto.toChatRoomEntity());

        }
    }

    @Override
    public void checkLastTime(ChatDTO dto) {
        ChatIDEntity chatID = new ChatIDEntity();
        chatID.setUserId(dto.getUserId());
        chatID.setChatRoomId(dto.getChatRoomId());
        System.out.println("==============================");
        Optional<ChatEntity> oce =chatJPA.findById(chatID);
        System.out.println("=============================="+oce.get());
        if(oce.isPresent()){
            System.out.println("==============================");
            chatJPA.save(dto.toChatEntity());
            System.out.println("==============================");
        }

    }

    @Override
    public List<ChatCheckDTO> chatAlarm(Long id) {
        int totalCount=0;
        List<ChatCheckDTO> checkList = new ArrayList<>();
       List<ChatEntity> chatlist=chatJPA.findByUserId(id);
       for(ChatEntity ce:chatlist){
           System.out.println("============================chatAlarm"+ce.getLastTime());
           List<ChatMessageEntity> messagelist = chatMessageJPA.findByChatRoomIdAndRegGreaterThan(ce.getChatRoomId(),ce.getLastTime());
           System.out.println("============================chatAlarm"+messagelist);
          if(messagelist.size()>0){
              ChatCheckDTO dto = new ChatCheckDTO();
              dto.setCount(messagelist.size());
              dto.setName(messagelist.get(0).getChatRoom().getName());
              dto.setChatRoomId(messagelist.get(0).getChatRoomId());
              dto.setId(id);
              checkList.add(dto);
              System.out.println("============================chatAlarm"+dto);
              totalCount=totalCount+messagelist.size();
          }


       }
        return checkList;
    }


}
