package com.example.image;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;


/**
 * UserController.
 */
@RestController
public class UserController {

    @Autowired
    ResourceLoader resourceloader;

    @GetMapping("/image")
    public ResponseEntity<byte[]> getCurrentAvatar() throws IOException {


        File avatar = getAnonymousUserAvatar();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType("image/jpg"))
                .contentLength(avatar.length())
                .body(Files.readAllBytes(avatar.toPath()));
    }

    public File getAnonymousUserAvatar() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        return resourceloader.getResource("anonymous.jpg").getFile();
    }
}
