package springboot.shuttle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

//채팅 정보
@Data
@AllArgsConstructor // 모든 변수를 파라미터로 갖는 생성자 생성
@NoArgsConstructor //기본 생성자 생성
public class ChatMessageDTO {
    private String roomId;
    private String writer;
    private String message;
    private LocalDateTime createdAt;
}
