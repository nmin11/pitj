package fascinating.pitj.entity;

import fascinating.pitj.dto.DestinationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Destination {

    @Id @GeneratedValue
    @Column(name = "destination_id")
    private Long id;

    @NotEmpty
    private String theme;

    @NotEmpty
    private String destination_name;

    @NotEmpty
    private String location;

    private Double lat;

    private Double lng;

    @NotEmpty
    private String attraction;

    private String tags;

    @NotEmpty
    private String description;

    private Double star;

    @OneToMany(mappedBy = "destination", cascade = ALL)
    private List<DestinationPicture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = ALL)
    private List<Review> reviews = new ArrayList<>();

    public Destination(DestinationDto destinationDto) {
        this.theme = destinationDto.getTheme();
        this.destination_name = destinationDto.getDestination_name();
        this.location = destinationDto.getLocation();
        this.lat = destinationDto.getLat();
        this.lng = destinationDto.getLng();
        this.attraction = destinationDto.getAttraction();
        this.tags = destinationDto.getTags();
        this.description = destinationDto.getDescription();
    }

}
