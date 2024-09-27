package lighting.messenger.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmpDTO {
	private int empId;
	private String empName;
	private String empEmail;
	private String empMP;
	private String empMemo;
	private String empHP;
	private String empHomeAddress;
	private String empHomeFax;
	private LocalDate empBirthday;
	private String empSign;
	private PositionDTO position;
	private String accountId;
	private String accountPw;

	public EmpDTO(int empId, String empName) {
		this.empId = empId;
		this.empName = empName;

	}
}
