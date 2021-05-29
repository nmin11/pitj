package fascinating.pitj.entity;

import fascinating.pitj.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static fascinating.pitj.entity.Role.*;
import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Enumerated(STRING)
    private Role authority;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    private String themes;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Member(String nickname, String password, String email, String themes) {
        this.authority = GENERAL;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.themes = themes;
    }

    public Member(String nickname, String password, String email) {
        this.authority = ADMIN;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public Member(MemberDto memberDto) {
        this.authority = GENERAL;
        this.nickname = memberDto.getNickname();
        this.password = memberDto.getPassword();
        this.email = memberDto.getEmail();
        this.themes = memberDto.getThemes();
    }

}
