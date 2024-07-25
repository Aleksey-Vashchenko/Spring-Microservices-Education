package com.vashchenko.micro.edu.menuservice.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class MenuItem {
    Long id;
    String name;
    Double price;
    String description;
    Integer weight;
}
