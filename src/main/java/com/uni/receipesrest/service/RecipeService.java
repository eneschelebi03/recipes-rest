package com.uni.receipesrest.service;

import com.uni.receipesrest.mapper.RecipeMapper;
import com.uni.receipesrest.model.Recipe;
import com.uni.receipesrest.model.dto.RecipeDto;
import com.uni.receipesrest.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeMapper recipeMapper, RecipeRepository recipeRepository) {
        this.recipeMapper = recipeMapper;
        this.recipeRepository = recipeRepository;
    }


    public RecipeDto getRecipe(String id) {

        UUID uuid = UUID.fromString(id);
        Recipe recipeById = this.recipeRepository.getReferenceById(uuid);

        return this.recipeMapper.recipeToRecipeDto(recipeById);
    }
}
