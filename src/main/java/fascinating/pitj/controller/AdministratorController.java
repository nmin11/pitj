package fascinating.pitj.controller;

import fascinating.pitj.dto.DestinationDto;
import fascinating.pitj.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdministratorController {

    private final DestinationService destinationService;

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
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) throws Exception {
        if (result.hasErrors()) {
            return "admin/newDestination";
        }

        else {
            destinationService.clarify(form);
            destinationService.imageStore(form.getTheme(), form.getDestination_name(), file);
            return "redirect:/";
        }
    }

}
