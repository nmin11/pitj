package fascinating.pitj.controller;

import fascinating.pitj.dto.ReviewFormDto;
import fascinating.pitj.entity.Destination;
import fascinating.pitj.entity.Member;
import fascinating.pitj.entity.Review;
import fascinating.pitj.repository.MemberRepository;
import fascinating.pitj.service.DestinationService;
import fascinating.pitj.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberRepository memberRepository;
    private final DestinationService destinationService;

    @PostMapping("/review/submit")
    public String reviewSubmit(ReviewFormDto formDto, String destinationId, @AuthenticationPrincipal User user) {
        Member member = memberRepository.findByNickname(user.getUsername()).get();
        Long destination_id = Long.parseLong(destinationId);
        Destination destination = destinationService.findById(destination_id);
        Review review = new Review(formDto, member, destination);
        reviewService.saveReview(review);
        return "redirect:/destination?id=" + destinationId;
    }

}
