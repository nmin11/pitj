package fascinating.pitj.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Theme {

    @Id @GeneratedValue
    @Column(name = "theme_id")
    private Long id;

    @NotEmpty
    private String theme_name;

    @OneToMany(mappedBy = "theme")
    private List<Destination> destinations = new ArrayList<>();

}
