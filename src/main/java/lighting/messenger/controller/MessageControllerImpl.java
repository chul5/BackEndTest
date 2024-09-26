package lighting.messenger.controller;

import lighting.messenger.controller.MessageController;
import lighting.messenger.dto.ChatMessageDTO;
import lighting.messenger.dto.ChatRoomDTO;
import lighting.messenger.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MessageControllerImpl {

    private final MessageServiceImpl messageService;
    @PostMapping(value = "/api/rooms/messages")
    public List<ChatMessageDTO> getMessagesByRoomId(@RequestBody ChatRoomDTO currentChat) {
        return messageService.getMessagesByRoomId(currentChat.getRoomId(), currentChat.getMyEmpId());
    }
}


