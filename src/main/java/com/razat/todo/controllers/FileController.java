package com.razat.todo.controllers;
import com.razat.todo.dtos.UploadFileResponseDTO;
import com.razat.todo.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    private ImageService imageService;

    @Autowired
    public FileController(ImageService imageService){
        this.imageService = imageService;
    }
    @PostMapping("/single")
    public ResponseEntity<UploadFileResponseDTO> uploadSingleFile(@RequestParam("file")MultipartFile file){
        logger.info("uploading single file!!");
        UploadFileResponseDTO uploadFileResponseDTO = UploadFileResponseDTO
                .builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();
        logger.info("{} succesfully uploaded!!",file.getOriginalFilename());
        return new ResponseEntity<>(uploadFileResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<UploadFileResponseDTO>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
        logger.info("Uploading multiple files!");
        List<UploadFileResponseDTO> responseDTOList = new ArrayList<>();
        for(MultipartFile file : files){
            responseDTOList.add(UploadFileResponseDTO
                    .builder()
                            .name(file.getOriginalFilename())
                            .size(file.getSize())
                            .contentType(file.getContentType())
                    .build());
            logger.info("{} succesfully uploaded!!",file.getOriginalFilename());
        }
        logger.info("All files uploaded successfully!!!");
        return new ResponseEntity<>(responseDTOList,HttpStatus.CREATED);
    }

    @GetMapping(path="/image/{name}",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> serveSingleImageHandler(@PathVariable("name") String name){
        return new ResponseEntity<>(imageService.getImage(name),HttpStatus.FOUND);
    }
}
