package fascinating.pitj.repository;

import fascinating.pitj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);

    Long findIdByNickname(String nickname);
}
