package fascinating.pitj.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "member")
    private List<Theme> themes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    public Member(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}
