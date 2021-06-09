package fascinating.pitj.controller;

import fascinating.pitj.dto.DestinationDto;
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
                                     RedirectAttributes redirectAttributes) throws Exception {
        System.out.println("--- 여행지 추가 기능 Controller 반응 ---");
        System.out.println("form.getDestination_name() = " + form.getDestination_name());
        System.out.println("form.getTheme() = " + form.getTheme());
        System.out.println("form.getAttraction() = " + form.getAttraction());
        System.out.println("form.getDescription() = " + form.getDescription());
        System.out.println("form.getDestination_name() = " + form.getTags());
        System.out.println("form.getLat() = " + form.getLat());
        System.out.println("form.getLng() = " + form.getLng());
        System.out.println("file.size() = " + file.size());
        System.out.println("file.get(0).getOriginalFilename() = " + file.get(0).getOriginalFilename());

        destinationService.clarify(form);
        destinationService.imageStore(form.getTheme(), form.getDestination_name(), file);
        return "redirect:/";
    }

}
