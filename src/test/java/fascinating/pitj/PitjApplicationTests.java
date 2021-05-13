package fascinating.pitj;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fascinating.pitj.entity.Member;
import fascinating.pitj.entity.QMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
		Member test_member = new Member("로코", "1234", "abc@def.com");
		em.persist(test_member);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QMember qMember = new QMember("t");

		Member result = query
				.selectFrom(qMember)
				.fetchOne();

		assertThat(result).isEqualTo(test_member);
		assertThat(result.getId()).isEqualTo(test_member.getId());
	}

}
