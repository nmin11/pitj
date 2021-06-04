package fascinating.pitj.service;

import fascinating.pitj.dto.DestinationDto;
import fascinating.pitj.entity.Destination;
import fascinating.pitj.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public Long clarify(DestinationDto form) {
        Destination destination = new Destination(form);
        destinationRepository.save(destination);
        return destination.getId();
    }

}
