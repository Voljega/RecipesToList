package com.corp.recipestolist.dao;

import com.corp.recipestolist.model.ShoppingList;
import com.corp.recipestolist.model.Ingredient;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingListDAO {

    private List<ShoppingList> shoppingLists = new ArrayList<>();

    public ShoppingList createShoppingList(List<Ingredient> productList) {
        Map<Ingredient,Float> ingredients = new HashMap();
        // Create Map to total quantity by unified ingredients
        productList.stream().forEach(i -> ingredients.merge(i,i.getQuantity(),Float::sum));
        // Reset quantity on unified ingredient and generate set
        // Not very clean, should clone each ingredient of the keyset to avoid modifying quantity of original elements !
        ingredients.keySet().stream().forEach(i -> i.setQuantity(ingredients.get(i)));
        ShoppingList newShoppinglist = new ShoppingList(ingredients.keySet());
        shoppingLists.add(newShoppinglist);
        return newShoppinglist;
    }

    public ShoppingList getShoppingList(String uuid){
        List<ShoppingList> shoppingListId =  shoppingLists.stream().filter(s -> s.getUuid().equals(uuid)).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(shoppingListId)?shoppingListId.get(0):null;
    }
}
