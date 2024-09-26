package lighting.messenger.service;

import lighting.messenger.dto.ChatRoomCreationRequest;
import lighting.messenger.dto.ChatRoomDTO;

import java.util.List;

public interface RoomService {
	public List<ChatRoomDTO> getRooms(Integer empId);

	public void createChattingRoom(ChatRoomCreationRequest group);
	public void deleteChatRoom(ChatRoomDTO currentChat);
}
