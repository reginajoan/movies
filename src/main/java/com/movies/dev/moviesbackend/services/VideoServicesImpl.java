package com.movies.dev.moviesbackend.services;

import com.movies.dev.moviesbackend.entity.Video;
import com.movies.dev.moviesbackend.exception.VideoNotFoundException;
import com.movies.dev.moviesbackend.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.List;


@Service
public class VideoServicesImpl implements VideoServices {

    private static final String FORMAT = "classpath:video/%s.mp4";

    @Autowired
    private VideoRepo repo;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Mono<Resource> getVideo(String name) {
        return Mono.fromSupplier(() -> this.resourceLoader.getResource(String.format(FORMAT, name)));
    }

    @Override
    public void saveVideo(MultipartFile file, Video video) {
        if(repo.existsByName(video.getName())){
            throw new VideoNotFoundException();
        }
        repo.save(video);
        fileStorageService.storeFile(file, video.getName());
    }

    @Override
    public List<String> getAllVideoNames() {
        return repo.getAllEntryNames();
    }
}
