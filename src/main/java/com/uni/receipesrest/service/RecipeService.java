package com.uni.receipesrest.service;

import com.uni.receipesrest.mapper.RecipeMapper;
import com.uni.receipesrest.model.Ingredient;
import com.uni.receipesrest.model.Quantity;
import com.uni.receipesrest.model.Recipe;
import com.uni.receipesrest.model.dto.RecipeDto;
import com.uni.receipesrest.repository.RecipeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final QuantityService quantityService;

    public RecipeService(RecipeMapper recipeMapper, RecipeRepository recipeRepository, IngredientService ingredientService, QuantityService quantityService) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
        this.quantityService = quantityService;
    }


    public RecipeDto getRecipe(String id) {

        UUID uuid = UUID.fromString(id);
        Recipe recipeById = this.recipeRepository.getReferenceById(uuid);

        return this.recipeMapper.recipeToRecipeDto(recipeById);
    }

    public List<RecipeDto> getAllRecipes() {

        return this.recipeRepository.findAll().stream()
                .map(recipeMapper::recipeToRecipeDto)
                .collect(Collectors.toList());
    }

    public void saveRecipeFromDto(RecipeDto recipeDto) {

        Recipe toSave = this.recipeMapper.recipeDtoToRecipe(recipeDto);

        this.saveRecipe(toSave);
    }



    public RecipeDto updateRecipe(String id, RecipeDto recipeDto) {

        Optional<Recipe> recipeOpt = this.recipeRepository.getRecipeById(UUID.fromString(id));

        if (recipeOpt.isEmpty()) {
            return null;
        }


        Recipe recipeToUpdate = recipeOpt.get();
        this.recipeRepository.delete(recipeToUpdate);
        recipeToUpdate = this.recipeMapper.recipeUpdateFromDto(recipeDto);
        
        return this.saveRecipe(recipeToUpdate);
    }

    @NotNull
    private RecipeDto saveRecipe(Recipe toSave) {
        Map<Ingredient, Quantity> savedIngredients = new LinkedHashMap<>();

        for (Map.Entry<Ingredient, Quantity> entry : toSave.getIngredients().entrySet()) {
            Ingredient ingredient = this.ingredientService.saveIngredient(entry.getKey());
            Quantity quantity = this.quantityService.saveQuantity(entry.getValue());

            savedIngredients.put(ingredient, quantity);
        }

        toSave.setIngredients(savedIngredients);

        return this.recipeMapper.recipeToRecipeDto(recipeRepository.save(toSave));
    }

    public RecipeDto deleteRecipe(String id) {

        Optional<Recipe> recipeOpt = this.recipeRepository.getRecipeById(UUID.fromString(id));

        if (recipeOpt.isEmpty()) {
            return null;
        }

        this.recipeRepository.delete(recipeOpt.get());
        return this.recipeMapper.recipeToRecipeDto(recipeOpt.get());
    }
}
