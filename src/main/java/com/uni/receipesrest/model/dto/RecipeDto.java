package com.uni.receipesrest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private String uuid;

    private String title;

    private String description;

    private Map<IngredientDto, QuantityDto> ingredients;
}
