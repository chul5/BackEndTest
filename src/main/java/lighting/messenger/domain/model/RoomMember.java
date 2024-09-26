package lighting.messenger.domain.model;

import jakarta.persistence.*;
import lighting.messenger.dto.EmpInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roomMember")
public class RoomMember {

    @Id
    @Column(name = "roomMember_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomMemberId;

    @JoinColumn(name = "emp_id")
    private Integer empId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private String name;

    private Boolean presentStatus;
    private Boolean notificationStatus;

    /**
     * Notification
     * False: 알림 없음
     * True : 알림 있음 (새메시지)
     */
    public static RoomMember createRoomMember(EmpInfo empInfo, Room room) {
        return RoomMember.builder()
                .empId(empInfo.getEmpId())
                .room(room)
                .name(empInfo.getEmpName())
                .presentStatus(true)
                .notificationStatus(false)
                .build();
    }

    public void updatePresentStatusFalse(){
        this.presentStatus = false;
    }

    public void updateNotificationStatusTrue() {
        this.notificationStatus = true;
    }
    public void updateNotificationStatusFalse() {
        this.notificationStatus = false;
    }
}