package fascinating.pitj.entity;

import fascinating.pitj.dto.ReviewFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @NotNull
    private int star;

    @NotEmpty
    private String review_content;

    private int recommend;

    public Review(ReviewFormDto formDto, Member member, Destination destination) {
        this.member = member;
        this.destination = destination;
        this.star = Integer.parseInt(formDto.getStar());
        this.review_content = formDto.getContent();
        this.recommend = 0;
    }

}
