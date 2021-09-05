package springboot.shuttle.Repository;

import org.springframework.stereotype.Repository;
import springboot.shuttle.domain.ChatRoomDTO;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatService {
    private Map<String, ChatRoomDTO> chatRoomDTOMap;

    //초기화 수행 메서드
    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    //전체 리스트를 조회하는 메서드
    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    //랜덤 룸ID 반환
    public ChatRoomDTO findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    //채팅방 생성
    public ChatRoomDTO createChatRoomDTO(String name){
        ChatRoomDTO room = ChatRoomDTO.create(name);
        chatRoomDTOMap.put(room.getRoomId(),room);

        return room;
    }
}
