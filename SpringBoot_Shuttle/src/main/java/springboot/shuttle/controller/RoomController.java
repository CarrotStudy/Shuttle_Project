package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.shuttle.Repository.ChatService;

//아직 진행중
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {
    private final ChatService service;
//
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
//    ==========================================

    //모든 채팅방 목록 반환
    @GetMapping(value = "/rooms")
    @ResponseBody
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("/chat/rooms"); //chat/rooms로 데이터 날리기

        mv.addObject("list",service.findAllRooms());

        return mv;
    }

    //addFlashAttribute()
    //
    //addFlashAttribute() 는 리다이렉트 직전 플래시에 저장하는 메소드다. 리다이렉트 이후에는 소멸한다.
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        log.info("# create chat room, name : "+name);
        rttr.addFlashAttribute("roomName",service.createChatRoomDTO(name));
        return "redirect:/chat/rooms";
    }

    @GetMapping("/room")
    public void getRoom(String roomId, Model model){
        log.info("get chat room :"+roomId);
        model.addAttribute("room",service.findRoomById(roomId));
    }
}
