package fascinating.pitj.service;

import fascinating.pitj.entity.Member;
import fascinating.pitj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateNickname(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateNickname(Member member) {
        //닉네임 중복 검증
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 닉네임입니다.");
        }
    }

}
