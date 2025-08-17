package lest.dev.CommerceMail.controller;

import lest.dev.CommerceMail.service.ImageService;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/product/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            List<Map<String, Object>> mapList = imageService.getImage(filename);

            Map<String, Object> mapContentType = mapList.get(0);
            Map<String, Object> mapContent = mapList.get(1);
            Resource resource = (Resource) mapContent.get("content");

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType( (String) mapContentType.get("contentType")))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
