package fascinating.pitj.controller;

import fascinating.pitj.dto.MemberDto;
import fascinating.pitj.valid.AccountValidator;
import fascinating.pitj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AccountValidator accountValidator;

    @GetMapping("/login")
    public String loginForm() {
        return "members/login";
    }

    @RequestMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "members/register";
    }

    @PostMapping("/members/logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberDto form, BindingResult result){
        accountValidator.validate(form, result);
        System.out.println(result.hasErrors());
        System.out.println(result.getFieldErrorCount());
        if (result.hasErrors()) {
            return "members/register";
        }

        else {
            memberService.join(form);
            return "members/login";
        }
    }

}
