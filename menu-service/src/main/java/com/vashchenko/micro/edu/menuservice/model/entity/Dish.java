package com.vashchenko.micro.edu.menuservice.model.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Dish {
    Long id;
    String name;
    Double price;
    String description;
    Integer weight;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Dish item = (Dish) object;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(price, item.price) && Objects.equals(description, item.description) && Objects.equals(weight, item.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, weight);
    }
}
