package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.shuttle.Repository.ChatService;
import springboot.shuttle.domain.ChatRoomDTO;
import springboot.shuttle.domain.Member;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//아직 진행중
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {
    private final ChatService service;

//    //채팅 리스트화면
//    @GetMapping("/room")
//    public String rooms(Model model){
//        return "/chat/room";
//    }
//
//    //모든 채팅방 목록 반환
//    @GetMapping(value = "/rooms")
//    @ResponseBody
//    public List<ChatRoomDTO> room(){
//        return service.findAllRooms();
//    }
//
//    //채팅방 생성
//    @PostMapping(value = "/room")
//    @ResponseBody
//   public ChatRoomDTO createRoom(@RequestParam String name) {
//        return service.createChatRoomDTO(name);
//    }
//
//    //채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(@PathVariable String roomId, Model model){
//      model.addAttribute("roomId",roomId);
//        return "/chat/roomdetail";
//    }
//
//    //특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoomDTO roomInfo(@PathVariable String roomId){
//        return service.findRoomById(roomId);
//    }
//
//
//
//
//
//
//    ==========================================

    //모든 채팅방 목록 반환
    @GetMapping("/chatroom")
    public String rooms(Model model, HttpServletRequest request){
//        Map<String, Object> list = new HashMap<>();
        log.info("# All Chat Rooms");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("username={}",member.getName());
        model.addAttribute("member", member);
        List<ChatRoomDTO> allRooms = service.findAllRooms();
        log.info("list={}",allRooms);
        model.addAttribute("list", allRooms);

        return "chat/chatroom";
    }

    //addFlashAttribute()
    //
    //addFlashAttribute() 는 리다이렉트 직전 플래시에 저장하는 메소드다. 리다이렉트 이후에는 소멸한다.
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        log.info("# create chat room, name : "+name);
        rttr.addFlashAttribute("roomName",service.createChatRoomDTO(name));
        return "redirect:/chat/chatroom";
    }

    @GetMapping("/room")
    public void getRoom(@RequestParam String roomId, Model model, HttpServletRequest request){
        log.info("get chat room :"+roomId);
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("member={}",member.getName());
        model.addAttribute("member",member);
        model.addAttribute("room",service.findRoomById(roomId));
    }
}
