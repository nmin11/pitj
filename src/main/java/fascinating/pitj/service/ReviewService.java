package fascinating.pitj.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fascinating.pitj.entity.QDestination;
import fascinating.pitj.entity.QMember;
import fascinating.pitj.entity.QReview;
import fascinating.pitj.entity.Review;
import fascinating.pitj.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EntityManager em;
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    QReview qReview = QReview.review;
    QMember qMember = QMember.member;
    QDestination qDestination = QDestination.destination;

    @Transactional
    public Boolean alreadyWrote(Long destination_id, Long member_id) {
        JPAQuery<Review> review =  queryFactory.selectFrom(qReview)
                .innerJoin(qReview.member, qMember).on(qMember.id.eq(member_id))
                .innerJoin(qReview.destination, qDestination).on(qDestination.id.eq(destination_id));

        if (review.equals(null)) {
            return false;
        } else {
            return true;
        }
    }
}
