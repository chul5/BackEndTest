package lighting.messenger.controller;

import lighting.messenger.controller.RoomController;
import lighting.messenger.dto.ChatRoomCreationRequest;
import lighting.messenger.dto.ChatRoomDTO;
import lighting.messenger.service.impl.MessageServiceImpl;
import lighting.messenger.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Log4j2
public class RoomControllerImpl {

	private final RoomServiceImpl roomService;
	private final MessageServiceImpl messageService;


	@GetMapping(value = "/api/rooms/{id}")
	public List<ChatRoomDTO> rooms(@PathVariable Integer id) {
		log.info("# 채팅방 목록 가져오기");
		return roomService.getRooms(id);
	}


	@PostMapping("/api/creation/room")
	public String createChatRoom(@RequestBody ChatRoomCreationRequest group) {
		log.info("Employee IDs: {}", group.getEmpInfos());
		roomService.createChattingRoom(group);
		return "Chat room created successfully!";
	}


	@PostMapping("/api/delRoom")
	public ResponseEntity<String> deleteChatRoom(@RequestBody ChatRoomDTO currentChat) {
		log.info("# 채팅방 삭제하기");
		roomService.deleteChatRoom(currentChat);
		return ResponseEntity.ok("상대방이 채팅방을 나갔습니다.");
	}
}
