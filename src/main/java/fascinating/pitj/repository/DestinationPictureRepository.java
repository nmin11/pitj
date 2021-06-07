package fascinating.pitj.repository;

import fascinating.pitj.entity.DestinationPicture;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DestinationPictureRepository extends Repository<DestinationPicture, Long> {

    DestinationPicture save(DestinationPicture destinationPicture);

    List<DestinationPicture> findAllByDestination(Long destination);

}
