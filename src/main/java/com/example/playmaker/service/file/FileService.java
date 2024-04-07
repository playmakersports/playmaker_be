package com.example.playmaker.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    public String fileUpload(MultipartFile multipartFile) throws IOException {
        Path serverPath = Paths.get(
                uploadDir
                        + File.separator +
                        StringUtils.cleanPath(multipartFile.getOriginalFilename()));

        try{
            Files.copy(multipartFile.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("upload error");
        }
        return serverPath.toString();
    }
}
