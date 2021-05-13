package fascinating.pitj.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Destination {

    @Id @GeneratedValue
    @Column(name = "destination_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @NotEmpty
    private String destination_name;

    @NotEmpty
    private String location;

    @NotEmpty
    private Double lat;

    @NotEmpty
    private Double lng;

    @NotEmpty
    private String attraction;

    @NotEmpty
    private String description;

    private Double star;

    @OneToMany(mappedBy = "destination", cascade = ALL)
    private List<Review> reviews = new ArrayList<>();

}
