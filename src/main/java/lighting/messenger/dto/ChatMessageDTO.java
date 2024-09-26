package lighting.messenger.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
	private Integer roomId;
	private Integer empId;
	private String writer;
	private String message;
	private String fileUrl;
	private String sendTime;
}
