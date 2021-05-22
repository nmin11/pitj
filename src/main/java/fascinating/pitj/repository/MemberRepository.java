package fascinating.pitj.repository;

import fascinating.pitj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickname(String nickname);

}
