package springboot.shuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shuttle.domain.ChatRoomDTO;
import springboot.shuttle.domain.Member;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

    void insertChatRoom(ChatRoomDTO chatRoom);

    List<ChatRoomDTO> chatRoomList(String username);

    ChatRoomDTO findByChatRoom(String id);

}
