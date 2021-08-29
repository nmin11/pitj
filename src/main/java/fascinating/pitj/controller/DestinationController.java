package fascinating.pitj.controller;

import fascinating.pitj.entity.Destination;
import fascinating.pitj.entity.DestinationPicture;
import fascinating.pitj.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping("/all")
    public String allDestination(Model model) {
        List<Destination> destinations = destinationService.findAll();
        model.addAttribute("destinations", destinations);
        return "/menus/allDestination";
    }

    @GetMapping("/destination")
    public String getDestination(Model model, @RequestParam("id") Long id) {
        Destination destination = destinationService.findById(id).get();
        List<DestinationPicture> destinationPictures = destinationService.findPictures(destination.getId());
        model.addAttribute("destination", destination);
        model.addAttribute("pictures", destinationPictures);
        return "/destination";
    }

}
