package com.uni.receipesrest.service;

import com.uni.receipesrest.model.Ingredient;
import com.uni.receipesrest.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    public Ingredient saveIngredient(Ingredient ingredient) {

        Optional<Ingredient> ingredientOpt = this.ingredientRepository.findByName(ingredient.getName());

        return ingredientOpt.orElseGet(() -> this.ingredientRepository.save(ingredient));

    }
}
