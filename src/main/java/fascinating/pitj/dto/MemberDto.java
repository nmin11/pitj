package fascinating.pitj.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class MemberDto {

    private String nickname;
    private String password;
    private String email;
    private String themes;

    @Builder
    public MemberDto(String nickname, String password, String email, String themes) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.themes = themes;
    }

}
