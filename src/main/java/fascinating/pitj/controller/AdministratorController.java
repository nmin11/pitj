package fascinating.pitj.controller;

import fascinating.pitj.dto.DestinationDto;
import fascinating.pitj.entity.Destination;
import fascinating.pitj.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdministratorController {

    private final DestinationService destinationService;
    private final EntityManager em;

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/link";
    }

    @RequestMapping("/admin/newDestination")
    public String newDestinationForm(Model model) {
        model.addAttribute("destinationDto", new DestinationDto());
        return "admin/newDestination";
    }

    @PostMapping("/destination/new")
    public String clarifyDestination(@Valid DestinationDto form,
                                     @RequestParam("image") List<MultipartFile> file,
                                     RedirectAttributes redirectAttributes) throws Exception {
        Long destination_id = destinationService.clarify(form);
        Destination destination = em.find(Destination.class, destination_id);
        destinationService.imageStore(destination, form.getTheme(), form.getDestination_name(), file);
        return "redirect:/";
    }

}
