package lighting.messenger.repository;

import lighting.messenger.domain.model.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMember, Integer> {

	@Query("select rm from RoomMember rm where rm.empId = :id and rm.presentStatus = true")
	List<RoomMember> findRoomIdsByEmpIdPresentStatusTrue(@Param("id") Integer id);

	@Query("select rm.name from RoomMember rm where rm.room.roomId = :roomId and rm.empId <> :empId")
	List<String> findNamesByRoomIdExceptionMe(@Param("roomId") Integer roomId, @Param("empId") Integer myId);

	@Query("select rm from RoomMember rm where rm.room.roomId = :roomId and rm.empId =:empId")
	RoomMember findRoomMemberByRoomIdAndEmpId(@Param("roomId") Integer roomId, @Param("empId") Integer empId);

	// Room ID로 RoomMember 엔티티 리스트 가져오기
	List<RoomMember> findByRoomRoomId(Integer roomId);

	@Query("select rm from RoomMember rm where rm.room.roomId = :roomId and rm.empId <> :empId")
	List<RoomMember> findRoomMembersByRoomIdExceptionMe(@Param("roomId") Integer roomId, @Param("empId") Integer empId);
}
