package fascinating.pitj.valid;

import fascinating.pitj.dto.MemberDto;
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
        return MemberDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (memberRepository.findByNickname(((MemberDto) target).getNickname()).isPresent()) {
            errors.rejectValue("nickname", "key", "이미 사용중인 닉네임입니다.");
        }
    }

}
