package springboot.shuttle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springboot.shuttle.domain.ChatRoomDTO;
import springboot.shuttle.domain.Member;
import springboot.shuttle.mapper.BoardMapper;
import springboot.shuttle.mapper.ChatRoomMapper;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Autowired
    private MemberService memberService;

    //전체 리스트를 조회하는 메서드
    public List<ChatRoomDTO> findAllRooms(String username){
        return chatRoomMapper.chatRoomList(username);
    }

    //랜덤 룸ID로 찾기
    public ChatRoomDTO findRoomById(String id){
        return chatRoomMapper.findByChatRoom(id);
    }

    //채팅방 생성
    public ChatRoomDTO createChatRoom(String name, String seller, String buyer){
        ChatRoomDTO chatRoom = ChatRoomDTO.builder()
                .roomId(UUID.randomUUID().toString())
                .name(name)
                .seller(seller)
                .buyer(buyer)
                .build();
        chatRoomMapper.insertChatRoom(chatRoom);
        return chatRoom;
    }

    public Member findByUsername(String loginId){
        return memberService.findByLoginId(loginId).orElseThrow();
    }

}
