package com.movies.dev.moviesbackend.services;

import com.movies.dev.moviesbackend.entity.Video;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public interface VideoServices {
    Mono<Resource> getVideo(String name);

    void saveVideo(MultipartFile file, Video video);

    List<String> getAllVideoNames();
}
