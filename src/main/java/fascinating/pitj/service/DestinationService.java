package fascinating.pitj.service;

import fascinating.pitj.dto.DestinationDto;
import fascinating.pitj.entity.Destination;
import fascinating.pitj.entity.DestinationPicture;
import fascinating.pitj.repository.DestinationPictureRepository;
import fascinating.pitj.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationPictureRepository destinationPictureRepository;

    @Transactional
    public Long clarify(DestinationDto form) {
        Destination destination = new Destination(form);
        destinationRepository.save(destination);
        return destination.getId();
    }

    @Transactional
    public List<DestinationPicture> imageStore(Destination destination, String theme, String destination_name, List<MultipartFile> multipartFiles) throws Exception {
        List<DestinationPicture> fileList = new ArrayList<>();

        if (multipartFiles.isEmpty()) {
            return fileList;
        }

        String absolutePath = new File("").getAbsolutePath() + "\\";
        String path = "src/main/resources/static/images/" + theme + "/" + destination_name;
        String relatePath = "/images/" + theme + "/" + destination_name;
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        int index = 1;
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileExtension;

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    } else {
                        break;
                    }
                }

                DestinationPicture destinationPicture = new DestinationPicture(destination, multipartFile.getOriginalFilename(),
                        relatePath + "/" + destination_name + index, multipartFile.getSize());
                fileList.add(destinationPicture);

                file = new File(absolutePath + path + "/" + destination_name + index + originalFileExtension);
                multipartFile.transferTo(file);

                destinationPictureRepository.save(destinationPicture);
            }
            index++;
        }

        return fileList;
    }

    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> findById(Long id) {
        return destinationRepository.findById(id);
    }

    public List<DestinationPicture> findPictures(Long id) {
        return destinationPictureRepository.findAllByDestinationId(id);
    }

}
