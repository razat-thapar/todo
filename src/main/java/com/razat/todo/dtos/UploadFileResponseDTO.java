package com.razat.todo.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UploadFileResponseDTO {
    private String name;
    private Long size;
    private String contentType;
}
