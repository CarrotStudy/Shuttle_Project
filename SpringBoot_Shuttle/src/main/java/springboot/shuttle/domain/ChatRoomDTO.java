package springboot.shuttle.domain;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//채팅방 정보
@Data
public class ChatRoomDTO {
    private String roomId; //채팅방 번호
    private String name;   //이름
    private Set<WebSocketSession> sessions = new HashSet<>(); //세션 정보

    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();  //랜덤 생성 키
        room.name = name;  //이름
        return room;
    }
}
