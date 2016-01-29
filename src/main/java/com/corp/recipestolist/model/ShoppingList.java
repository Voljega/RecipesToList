package com.corp.recipestolist.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ShoppingList {

    private UUID uuid;

    private Set<Ingredient> ingredients = new HashSet<>();

    public ShoppingList(){
        this.uuid = UUID.randomUUID();
    };

    public ShoppingList(Set<Ingredient> ingredients){
        this.uuid = UUID.randomUUID();
        this.ingredients = ingredients;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
