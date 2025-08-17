package lest.dev.CommerceMail.controller;

import lest.dev.CommerceMail.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/upload")
@RequiredArgsConstructor
public class UploadController {

    public final UploadService uploadService;

    @PostMapping("/create")
    public ResponseEntity<String> uploadFile(@RequestParam("productImg") MultipartFile file) {
        return ResponseEntity.ok().body(uploadService.uploadFile(file));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFile(@RequestParam("productImg") MultipartFile file,
                                             @RequestParam("imgName") String imgName) {
        return ResponseEntity.ok().body(uploadService.updateFile(file, imgName));
    }

}
