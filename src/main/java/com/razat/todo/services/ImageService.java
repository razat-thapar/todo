package com.razat.todo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ImageService {
    @Value("${com.razat.todo.image.path}")
    private String imagePath;
    private Logger logger = LoggerFactory.getLogger(ImageService.class);
    public byte[] getImage(String name){
        logger.info("Fetching the following image name: {}",imagePath+name);
        //image.
        try(FileInputStream fileInputStream = new FileInputStream(imagePath+name);){
            logger.info("converting the image to byte[]");
            return fileInputStream.readAllBytes();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
