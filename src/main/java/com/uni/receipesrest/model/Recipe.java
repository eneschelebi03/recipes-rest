package com.uni.receipesrest.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipes")
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredients",
            joinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "quantity_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "ingredient_id")
    private Map<Ingredient, Quantity> ingredients;


}
