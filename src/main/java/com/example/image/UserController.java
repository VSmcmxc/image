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

import java.io.File;
import java.io.FileNotFoundException;
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


    public File loadEmployeesWithSpringInternalClass()
            throws FileNotFoundException {
        return ResourceUtils.getFile(
                "classpath:anonymous.jpg");
    }



    @GetMapping("/image")
    public ResponseEntity<byte[]> getCurrentAvatar() throws IOException {


        File avatar = loadEmployeesWithSpringInternalClass();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType("image/jpg"))
                .contentLength(avatar.length())
                .body(Files.readAllBytes(avatar.toPath()));
    }

   /* public File getAnonymousUserAvatar() throws IOException {
        return getFileFromResources("anonymous.jpg");
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }*/
}
