package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.shuttle.service.ChatService;
import springboot.shuttle.domain.Board;
import springboot.shuttle.domain.ChatRoomDTO;
import springboot.shuttle.domain.Member;
import springboot.shuttle.service.BoardService;
import springboot.shuttle.service.MemberService;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

//아직 진행중
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {
    private final ChatService service;

    @Autowired
    private BoardService boardService;

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
        log.info("# All Chat Rooms");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("username={}",member.getName());
        model.addAttribute("member", member);
//        Member findUsername = service.findByUsername(member.getLoginId());
        List<ChatRoomDTO> allRooms = service.findAllRooms(member.getLoginId());
        log.info("list={}",allRooms);
        model.addAttribute("list", allRooms);

        return "chat/chatroom";
    }

    //addFlashAttribute()
    //
    //addFlashAttribute() 는 리다이렉트 직전 플래시에 저장하는 메소드다. 리다이렉트 이후에는 소멸한다.
//    @PostMapping(value = "/room")
//    public String create(@RequestParam String name, RedirectAttributes rttr){
//        log.info("# create chat room, name : "+name);
//        rttr.addFlashAttribute("roomName",service.createChatRoomDTO(name));
//        return "redirect:/chat/chatroom";
//    }

//    /*
//     * 중복회원을 검증하고 결과 값을 알려주는 api
//     * */
//    @PostMapping(value = "/api/v1/check/id/duplicate")
//    Map<String, Object> isDuplicateId(@RequestParam("user_id") String id){
//        Map<String, Object> map = new HashMap<>();
//        System.out.println(id);
//        if(employeeService.isDuplicatedId(id)){
//            //중복이라고 반환
//            //TODO result_cd 부분이 미정이라서 일단 실패코드 반환
//            map.put("result_cd",5001);
//            map.put("result_message", "중복된 아이디 입니다.");
//        }else{
//            //성공 반환환다.
//            map.put("result_cd",2001);
//            map.put("result_message", "");
//            map.put("data", id);
//        }
//        return map;
//    }

//    public String create(@RequestParam int dno,RedirectAttributes rttr, HttpServletRequest request ){
//        ChatRoomDTO roomDTO = new ChatRoomDTO();
//        log.info("여기 {}",dno);
//        Board board = new Board();
//        String chattitle = board.getTitle();
//        HttpSession session = request.getSession();
////        log.info("# create chat room, name : "+name);
//        rttr.addFlashAttribute("roomName",service.createChatRoomDTO(chattitle));
//        return "redirect:/board/add";
//    }


    @GetMapping(value = "/room/{bno}")
    public String create(@PathVariable Long bno, RedirectAttributes rttr, HttpServletRequest request){
        log.info("bno={}",bno);
        String chatRoom = boardService.findByBoardName(bno);
        String seller = boardService.findByWriter(bno);
        HttpSession session = request.getSession();
        Member buyer = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        ChatRoomDTO chatroom = service.createChatRoom(chatRoom, seller, buyer.getLoginId());

        rttr.addFlashAttribute("roomName", chatroom);
//        return "redirect:/chat/chatroom";
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
