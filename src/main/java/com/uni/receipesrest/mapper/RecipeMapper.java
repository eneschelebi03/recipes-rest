package com.uni.receipesrest.mapper;

import com.uni.receipesrest.model.Ingredient;
import com.uni.receipesrest.model.Quantity;
import com.uni.receipesrest.model.Recipe;
import com.uni.receipesrest.model.dto.IngredientDto;
import com.uni.receipesrest.model.dto.QuantityDto;
import com.uni.receipesrest.model.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class RecipeMapper {

    private final IngredientMapper ingredientMapper;
    private final QuantityMapper quantityMapper;

    protected RecipeMapper(IngredientMapper ingredientMapper, QuantityMapper quantityMapper) {
        this.ingredientMapper = ingredientMapper;
        this.quantityMapper = quantityMapper;
    }

    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "recipeIngredientsMapping")
    public abstract RecipeDto recipeToRecipeDto(Recipe recipe);

    @Named("recipeIngredientsMapping")
    Map<IngredientDto, QuantityDto> recipeIngredientsMapping(Map<Ingredient, Quantity> ingredientsSrc) {
        Map<IngredientDto, QuantityDto> ingredients = new LinkedHashMap<>();

        for (Map.Entry<Ingredient, Quantity> entry: ingredientsSrc.entrySet()) {
            IngredientDto ingredientDto = this.ingredientMapper.ingredientToIngredientDto(entry.getKey());
            QuantityDto quantityDto = this.quantityMapper.quantityToQuantityDto(entry.getValue());

            ingredients.put(ingredientDto, quantityDto);
        }

        return ingredients;
    }

}
