package lighting.messenger.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ChatRoomCreationRequest {
	private List<EmpInfo> empInfos;  // EmpInfo 리스트로 변경
	private String roomName;
}
