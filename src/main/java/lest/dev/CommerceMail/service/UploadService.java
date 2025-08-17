package lest.dev.CommerceMail.service;

import lest.dev.CommerceMail.exception.product.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;      
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {

    @Value("${file.upload-dir}")
    private Path uploadDir;

    public String uploadFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            File dir = new File(uploadDir.toString());

            String typeFile = file.getContentType().substring("image/".length());

            Path targetPath = this.uploadDir.resolve(
                Paths.get("imageId" + "_" + dir.listFiles().length + "." + typeFile))
                .normalize().toAbsolutePath();

            if (!targetPath.getParent().equals(this.uploadDir.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return targetPath.getFileName().toString();


        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public String updateFile(MultipartFile file, String nameFile) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }


            Path targetPath = this.uploadDir.resolve(
                            Paths.get(nameFile))
                    .normalize().toAbsolutePath();

            if (!targetPath.getParent().equals(this.uploadDir.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return targetPath.getFileName().toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
