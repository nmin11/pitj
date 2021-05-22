package fascinating.pitj.controller.member;

import fascinating.pitj.entity.Member;
import fascinating.pitj.service.AccountValidator;
import fascinating.pitj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AccountValidator accountValidator;

    @RequestMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/joinForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        accountValidator.validate(form, result);
        System.out.println(result.hasErrors());
        System.out.println(result.getFieldErrorCount());
        if (result.hasErrors()) {
            return "members/joinForm";
        }

        else {
            Member member = new Member(form.getNickname(), form.getPassword(), form.getEmail(), form.getThemes());
            memberService.join(member);
            return "redirect:/";
        }
    }

}
