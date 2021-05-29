package fascinating.pitj.repository;

import fascinating.pitj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickname(String nickname);

    @Query("select m from Member m where m.nickname = :nickname")
    Optional<Member> findByNicknameForLogin(@Param("nickname") String nickname);

}
