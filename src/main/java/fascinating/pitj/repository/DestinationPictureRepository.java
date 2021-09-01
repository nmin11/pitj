package fascinating.pitj.repository;

import fascinating.pitj.entity.Destination;
import fascinating.pitj.entity.DestinationPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationPictureRepository extends JpaRepository<DestinationPicture, Long> {
    List<DestinationPicture> findAllByDestination(Destination destination);
}
