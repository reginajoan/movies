package com.movies.dev.moviesbackend.services;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    public FileStorageService(Environment env){
        this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir","./src/main/resources/video"))
                .toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new RuntimeException("Could not create the directory", e);
        }
    }

    private String getfileExtension(String fileName){
        if(fileName == null){
            return fileName;
        }
        String[] fileNameParts = fileName.split("\\.");
        return fileNameParts[fileNameParts.length -1];
    }

    public String storeFile(MultipartFile file, String name){
        String fileName = name + "." + getfileExtension(file.getOriginalFilename());

        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e){
            throw new RuntimeException("Could not store file " + fileName+ ". Please try again!", e);
        }
    }
}
