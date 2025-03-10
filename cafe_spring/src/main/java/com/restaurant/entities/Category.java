package com.restaurant.entities;

import com.restaurant.dtos.CategoryDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {

    /*ID declared as primary key*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;



    public CategoryDto getCategoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        categoryDto.setDescription (description);
        categoryDto.setReturnedImg(img);
        return categoryDto;
    }

}
