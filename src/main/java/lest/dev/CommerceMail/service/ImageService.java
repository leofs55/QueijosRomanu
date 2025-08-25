package lest.dev.CommerceMail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${file.upload-dir}")
    private Path imageDir;

    public List<Map<String, Object>> getImage(String filename) throws MalformedURLException {
        try {
            Path file = imageDir.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());

            String contentType = Files.probeContentType(file);

            if (contentType == null) {
                contentType = "application/octet-stream"; // fallback
            }
            if (!resource.exists()) {
                throw new FileNotFoundException();
            }

            return List.of(
                    Map.of("contentType", contentType),
                    Map.of("content", resource)
            );

        } catch (MalformedURLException e) {
            throw new MalformedURLException("Erro no UrlResource!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro: arquivo inexistente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
