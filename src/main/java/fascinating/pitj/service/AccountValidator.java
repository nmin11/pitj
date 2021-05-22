package fascinating.pitj.service;

import fascinating.pitj.controller.member.MemberForm;
import fascinating.pitj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;
        if (memberRepository.findByNickname(((MemberForm) target).getNickname()) != null) {
            errors.rejectValue("nickname", "key", "이미 사용중인 닉네임입니다.");
        }
    }

}
