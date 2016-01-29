package com.corp.recipestolist.dao;

import com.corp.recipestolist.model.Recipe;
import com.corp.recipestolist.model.ShoppingList;
import com.corp.recipestolist.model.Ingredient;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ShoppingListDAOTest {

    private ShoppingListDAO shoppingListDAO = new ShoppingListDAO();
    private RecipeDAO recipeDAO = new RecipeDAO();

    @Test
    public void createShoppingList_should_sum_ingredients_by_name() {

        Recipe recipeOne = recipeDAO.getRecipe(1);
        Recipe recipeTen = recipeDAO.getRecipe(10);

        Ingredient cherryTomatoOne = recipeOne.getIngredients().stream().filter(i -> i.getName().equals("cherry tomatoes"))
                .collect(Collectors.toList()).get(0);
        Ingredient cherryTomatoTen = recipeTen.getIngredients().stream().filter(i -> i.getName().equals("cherry tomatoes"))
                .collect(Collectors.toList()).get(0);

        Float quantityTomatoOne = cherryTomatoOne.getQuantity();
        Float quantityTomatoTen = cherryTomatoTen.getQuantity();

        List<Ingredient> twoRecipesIngredients = new ArrayList();
        twoRecipesIngredients.addAll(recipeOne.getIngredients());
        twoRecipesIngredients.addAll(recipeTen.getIngredients());
        ShoppingList shoppingList = shoppingListDAO.createShoppingList(twoRecipesIngredients);

        assertTrue(CollectionUtils.isNotEmpty(shoppingList.getIngredients()));
        assertThat(shoppingList.getIngredients().size()).isEqualTo(13);

        Ingredient cherryTomatoShoppingList = shoppingList.getIngredients().stream().filter(i -> i.getName().equals("cherry tomatoes"))
                .collect(Collectors.toList()).get(0);

        assertThat(cherryTomatoShoppingList.getQuantity())
                .isEqualTo(quantityTomatoOne + quantityTomatoTen);
    }
}