package fascinating.pitj.controller;

import fascinating.pitj.entity.Destination;
import fascinating.pitj.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

}
