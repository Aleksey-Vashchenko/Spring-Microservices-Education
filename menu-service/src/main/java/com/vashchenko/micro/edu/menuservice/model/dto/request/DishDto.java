package com.vashchenko.micro.edu.menuservice.model.dto.request;

public record DishDto(String name,
                      String description,
                      Double price,
                      Integer weight) {
}
