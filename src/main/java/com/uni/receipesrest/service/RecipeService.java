package com.uni.receipesrest.service;

import com.uni.receipesrest.mapper.RecipeMapper;
import com.uni.receipesrest.model.Ingredient;
import com.uni.receipesrest.model.Quantity;
import com.uni.receipesrest.model.Recipe;
import com.uni.receipesrest.model.dto.RecipeDto;
import com.uni.receipesrest.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    public void saveRecipe(RecipeDto recipeDto) {

        Recipe toSave = this.recipeMapper.recipeDtoToRecipe(recipeDto);

        Map<Ingredient, Quantity> savedIngredients = new LinkedHashMap<>();

        for (Map.Entry<Ingredient, Quantity> entry : toSave.getIngredients().entrySet()) {
            Ingredient ingredient = this.ingredientService.saveIngredient(entry.getKey());
            Quantity quantity = this.quantityService.saveQuantity(entry.getValue());

            savedIngredients.put(ingredient, quantity);
        }

        toSave.setIngredients(savedIngredients);

        this.recipeRepository.save(toSave);
    }
}
