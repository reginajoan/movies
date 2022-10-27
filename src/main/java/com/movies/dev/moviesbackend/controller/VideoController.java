package com.movies.dev.moviesbackend.controller;

import com.movies.dev.moviesbackend.entity.Video;
import com.movies.dev.moviesbackend.services.VideoServices;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("video")
@AllArgsConstructor
public class VideoController {
    private VideoServices videoServices;

    @PostMapping()
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        Video video = new Video();
        video.setName(name);
        video.setDescription("this is video");
        videoServices.saveVideo(file, video);
        return ResponseEntity.ok("Video saved successfully");
    }

    @GetMapping(value = "/video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range){
        System.out.println("title : " + title);
        System.out.println("range : " + range);
        return videoServices.getVideo(title);
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity.ok(videoServices.getAllVideoNames());
    }

}
