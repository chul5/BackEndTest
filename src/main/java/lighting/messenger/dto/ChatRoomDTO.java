package lighting.messenger.dto;

import lighting.messenger.domain.model.RoomMember;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {

	private Integer roomId;
	private Integer myEmpId;
	private String names;
	private String myName;
	private Boolean notificationStatus;

	public static List<ChatRoomDTO> create(Map<RoomMember, List<String>> roomAndEmp, Integer myId){
		return roomAndEmp.entrySet().stream()
				.map(i -> new ChatRoomDTO(i.getKey().getRoom().getRoomId(),
						myId,
						String.join(",", i.getValue()),
						"coh",
						i.getKey().getNotificationStatus()
						))
				.collect(Collectors.toList());
	}
}
