package springboot.shuttle.domain;

import lombok.Data;

//채팅 정보
@Data
public class ChatMessageDTO {
    private String roomId; //룸이름
    private String writer; //대화방 사람들
    private String message; //메시지
}
