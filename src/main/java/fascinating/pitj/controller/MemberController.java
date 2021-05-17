package fascinating.pitj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @RequestMapping("/join")
    public String join() {
        log.info("join controller");
        return "members/joinForm";
    }

}
