package com.example.image;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;


/**
 * UserController.
 */
@RestController
public class UserController {

    @Autowired
    ResourceLoader resourceLoader;

    @Value("classpath:anonymous.jpg")
    private Resource resource;


    @GetMapping("/")
    public ResponseEntity<byte[]> getCurrentAvatar() throws IOException {

/*
        Resource resource = resourceLoader.getResource("classpath:anonymous.jpg");
        InputStream is = resource.getInputStream();

        byte[] result = IOUtils.toByteArray(is);

        //File avatar = resource.getFile();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType("image/jpg"))
                .contentLength(avatar.length())
                .body(Files.readAllBytes(avatar.toPath()));*/
        InputStream in = resource.getInputStream();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(IOUtils.toByteArray(in));
    }


}
