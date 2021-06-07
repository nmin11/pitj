package fascinating.pitj.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class DestinationPicture {

    @Id
    @GeneratedValue
    @Column(name = "destination_picture_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @NotEmpty
    private String original_file_name;

    @NotEmpty
    private String stored_file_name;

    private Long file_size;

    public DestinationPicture(String original_file_name, String stored_file_name, Long file_size) {
        this.original_file_name = original_file_name;
        this.stored_file_name = stored_file_name;
        this.file_size = file_size;
    }

}
