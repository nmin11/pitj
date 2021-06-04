package fascinating.pitj;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fascinating.pitj.dto.MemberDto;
import fascinating.pitj.entity.Member;
import fascinating.pitj.entity.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PitjApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		MemberDto memberDto = new MemberDto();
		memberDto.setNickname("테스트");
		memberDto.setPassword("1234");
		memberDto.setEmail("abc@def.com");
		memberDto.setThemes("romance");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		Member test_member = new Member(memberDto);
		em.persist(test_member);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QMember qMember = new QMember("t");

		Member result = query
				.selectFrom(qMember)
				.where(qMember.nickname.eq("테스트"))
				.fetchOne();

		assertThat(result).isEqualTo(test_member);
		assertThat(result.getId()).isEqualTo(test_member.getId());
	}

}
