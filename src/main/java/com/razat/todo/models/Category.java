package com.razat.todo.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category extends BaseModel{
    private String categoryName;
    @ManyToMany(mappedBy = "categoryList")
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();
}
