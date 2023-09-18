package com.uni.receipesrest.mapper;

import com.uni.receipesrest.model.Ingredient;
import com.uni.receipesrest.model.dto.IngredientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
}
