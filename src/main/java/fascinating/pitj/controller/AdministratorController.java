package fascinating.pitj.controller;

import fascinating.pitj.dto.DestinationDto;
import fascinating.pitj.dto.MemberDto;
import fascinating.pitj.service.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String clarifyDestination(@Valid DestinationDto form, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/newDestination";
        }

        else {
            destinationService.clarify(form);
            return "redirect:/";
        }
    }

}
