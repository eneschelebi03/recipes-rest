package com.uni.receipesrest.mapper;

import com.uni.receipesrest.model.Ingredient;
import com.uni.receipesrest.model.Quantity;
import com.uni.receipesrest.model.Recipe;
import com.uni.receipesrest.model.dto.QuantityDto;
import com.uni.receipesrest.model.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class RecipeMapper {


    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "ingredientsToDtoIngredients")
    public abstract RecipeDto recipeToRecipeDto(Recipe recipe);

    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "ingredientsDtoToIngredients")
    public abstract Recipe recipeDtoToRecipe(RecipeDto recipeDto);


    @Named("ingredientsDtoToIngredients")
    Map<Ingredient, Quantity>ingredientsDtoToIngredients(Map<String, QuantityDto> ingredientsSrc) {

        if (ingredientsSrc == null) {
            return null;
        }

        Map<Ingredient, Quantity> ingredients = new LinkedHashMap<>();

        for ( java.util.Map.Entry<String, QuantityDto> entry : ingredientsSrc.entrySet() ) {

            Ingredient key = new Ingredient();
            key.setName(entry.getKey());

            Quantity value = quantityDtoToQuantity( entry.getValue() );

            ingredients.put( key, value );
        }

        return ingredients;
    }

    @Named("ingredientsToDtoIngredients")
    Map<String, QuantityDto>ingredientsToDtoIngredients(Map<Ingredient, Quantity> ingredientsSrc) {
        if (ingredientsSrc == null) {
            return null;
        }

        Map<String, QuantityDto> ingredients = new LinkedHashMap<>();

        for ( java.util.Map.Entry<Ingredient, Quantity> entry : ingredientsSrc.entrySet() ) {
            String key = entry.getKey().getName();
            QuantityDto value = quantityToQuantityDto( entry.getValue() );
            ingredients.put( key, value );
        }

        return ingredients;
    }

    abstract QuantityDto quantityToQuantityDto(Quantity value);

    abstract Quantity quantityDtoToQuantity(QuantityDto value);

}
