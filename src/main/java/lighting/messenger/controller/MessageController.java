package lighting.messenger.controller;

import lighting.messenger.dto.ChatMessageDTO;
import lighting.messenger.dto.ChatRoomDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MessageController {

	public List<ChatMessageDTO> getMessagesByRoomId(@RequestBody ChatRoomDTO currentChat);
}
