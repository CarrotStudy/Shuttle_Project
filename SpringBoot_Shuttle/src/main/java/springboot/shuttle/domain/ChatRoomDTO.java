package springboot.shuttle.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//채팅방 정보
@Data
@Builder
public class ChatRoomDTO {
    private String roomId; //채팅방 번호
    private String name;   //이름
    private String seller;
    private String buyer;
//    private Set<WebSocketSession> sessions = new HashSet<>(); //세션 정보

}
