package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.shuttle.domain.ChatMessageDTO;
import springboot.shuttle.domain.Member;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//아직 진행중임
@Controller
@Log4j2
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message){
//        HttpSession session = request.getSession(); //ctrl+alt+v
        message.setMessage(message.getWriter()+"님이 채팅방에 참여하였습니다");
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        template.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);
    }
}
