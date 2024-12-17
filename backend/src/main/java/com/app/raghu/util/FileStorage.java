package com.app.raghu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileStorage {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final static String FliesPath = "D:/Study/Code/KaiFa/PropertyInCase/backend/src/main/resources/static/";

    public String storeFile(MultipartFile file) throws IOException {
        Path directory = Paths.get(uploadDir);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        // 为文件名添加时间戳
        String originalFileName = file.getOriginalFilename();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = timestamp + "_" + (originalFileName != null ? originalFileName : "file");

        Path targetPath = directory.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetPath);
        }

        // 返回相对路径，与 HTTP URL 保持一致
        return "imageText/" + fileName;
    }

    public void UpdatedFile(String relativePath) {
        try {
            File file = new File(FliesPath + relativePath); // BASE_DIRECTORY 是文件的基础路径
            if (file.exists()) {
                if (!file.delete()) {
                    throw new RuntimeException("Failed to delete file: " + relativePath);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file", e);
        }
    }


    public boolean deleteFile(String relativePath) {
        try {
            File file = new File(FliesPath + relativePath);
            if (file.exists()) {
                return file.delete();
            }
            return true; // 如果文件不存在，则认为删除成功
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

