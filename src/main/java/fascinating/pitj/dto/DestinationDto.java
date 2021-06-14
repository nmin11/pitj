package fascinating.pitj.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DestinationDto {

    private String theme;
    private String destination_name;
    private String location;
    private Double lat;
    private Double lng;
    private String tags;
    private String description;

}
