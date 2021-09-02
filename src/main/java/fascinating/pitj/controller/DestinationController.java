package fascinating.pitj.controller;

import fascinating.pitj.entity.Destination;
import fascinating.pitj.entity.DestinationPicture;
import fascinating.pitj.entity.Member;
import fascinating.pitj.service.DestinationService;
import fascinating.pitj.service.MemberService;
import fascinating.pitj.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;
    private final MemberService memberService;
    private final ReviewService reviewService;

    @GetMapping("/all")
    public String allDestination(Model model) {
        List<Destination> destinations = destinationService.findAll();
        model.addAttribute("destinations", destinations);
        return "/menus/allDestination";
    }

    @GetMapping("/destination")
    public String getDestination(Model model, @RequestParam("id") Long id, @AuthenticationPrincipal User user) {
        Destination destination = destinationService.findById(id).get();
        List<DestinationPicture> destinationPictures = destinationService.findPictures(destination);
        List<DestinationPicture> pictures = new ArrayList<>();
        for (int i = 1; i < destinationPictures.size(); i++) {
            pictures.add(destinationPictures.get(i));
        }

        model.addAttribute("destination", destination);
        model.addAttribute("firstPicture", destinationPictures.get(0));
        model.addAttribute("pictures", pictures);
        return "/destination";
    }

}
