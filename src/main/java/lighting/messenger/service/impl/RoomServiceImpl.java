package lighting.messenger.service.impl;

import lighting.messenger.domain.model.Room;
import lighting.messenger.domain.model.RoomMember;
import lighting.messenger.dto.ChatRoomCreationRequest;
import lighting.messenger.dto.ChatRoomDTO;
import lighting.messenger.repository.MessageRepository;
import lighting.messenger.repository.RoomMemberRepository;
import lighting.messenger.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl {
	private final RoomMemberRepository roomMemberRepository;
	private final RoomRepository roomRepository;
	private final MessageRepository messageRepository;

	@Transactional
	public List<ChatRoomDTO> getRooms(Integer empId) {
		// 참여중인 채팅방 목록 가져오기 (presentStatus = true)
		List<RoomMember> roomMembers = roomMemberRepository.findRoomIdsByEmpIdPresentStatusTrue(empId);
		Map<RoomMember, List<String>> roomAndEmp = roomMembers.stream()
				.collect(Collectors.toMap(
				roomMember -> roomMember,
				roomMember -> roomMemberRepository.findNamesByRoomIdExceptionMe(roomMember.getRoom().getRoomId(), empId)
		));
		return ChatRoomDTO.create(roomAndEmp, empId);
	}

	@Transactional
	public void createChattingRoom(ChatRoomCreationRequest group) {
		Room room = Room.createRoom(group.getRoomName());
		roomRepository.save(room);
		group.getEmpInfos().stream()
				.map(empInfo -> RoomMember.createRoomMember(empInfo, room))
				.forEach(roomMemberRepository::save);
	}

	@Transactional
	public void deleteChatRoom(ChatRoomDTO currentChat) {
		RoomMember roomMember = roomMemberRepository.findRoomMemberByRoomIdAndEmpId(currentChat.getRoomId(), currentChat.getMyEmpId());
		roomMember.updatePresentStatusFalse();
		List<RoomMember> roomMembers = roomMemberRepository.findByRoomRoomId(currentChat.getRoomId());
		Optional<Boolean> isUserOnRoom = roomMembers.stream().map(RoomMember::getPresentStatus)
				.filter(status -> status)
				.findAny();
		if (isUserOnRoom.isEmpty()) {
			roomMembers.stream().map(RoomMember::getRoomMemberId)
					.forEach(roomMemberId -> {
						messageRepository.deleteMessagesByRoomMemberId(roomMemberId);
						roomMemberRepository.deleteById(roomMemberId);
					});
			roomRepository.deleteById(currentChat.getRoomId());
		}
	}
}
