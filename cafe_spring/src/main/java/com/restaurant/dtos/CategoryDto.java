package com.restaurant.dtos;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

}
