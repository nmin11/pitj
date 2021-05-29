package fascinating.pitj.service;

import fascinating.pitj.dto.MemberDto;
import fascinating.pitj.entity.Member;
import fascinating.pitj.entity.Role;
import fascinating.pitj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fascinating.pitj.entity.Role.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = new Member(memberDto);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByNicknameForLogin(nickname);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userEntity.getAuthority().equals(ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(ADMIN.getValue()));
        } else if (userEntity.getAuthority().equals(GENERAL)) {
            authorities.add(new SimpleGrantedAuthority(GENERAL.getValue()));
        }

        return new User(userEntity.getNickname(), userEntity.getPassword(), authorities);
    }

}
