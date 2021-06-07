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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationPictureRepository destinationPictureRepository;

    public Long clarify(DestinationDto form) {
        Destination destination = new Destination(form);
        destinationRepository.save(destination);
        return destination.getId();
    }

    public List<DestinationPicture> imageStore(String theme, String destination_name, List<MultipartFile> multipartFiles) throws Exception {
        List<DestinationPicture> fileList = new ArrayList<>();

        if (multipartFiles.isEmpty()) {
            return fileList;
        }

        String absolutePath = new File("").getAbsolutePath() + "\\";
        String path = "images/" + theme;
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

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

                DestinationPicture destinationPicture = new DestinationPicture(multipartFile.getOriginalFilename(),
                        path + "/" + destination_name, multipartFile.getSize());
                fileList.add(destinationPicture);

                file = new File(absolutePath + path + "/" + destination_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
    }
}
