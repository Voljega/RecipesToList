var app = angular.module('recipes',[]);

app.controller('RecipesController', ['$http', function($http){
    var ctrl = this;
    
    ctrl.alerts = [];
    ctrl.recipes = [];

    this.getRecipes = function(){
        
        var uri = 'rs/recipes';
        
        $http.get(uri)
            .success(function (restRecipes) {
                ctrl.recipes = restRecipes;
            })
            .error(function(){
                ctrl.alerts.push({type: 'danger', msg: 'Technical error'});
            });
    };
}]);

app.controller('BasketController', ['$http', function($http){
    var ctrl = this;

    ctrl.shoppinglist = {};
    ctrl.products = [];
    //both following parameters could be removed and we could use shoppinglist ingredients and products length
    ctrl.noproducts = true;
    ctrl.gotList = false;

    ctrl.alerts = [];

    this.addToBasket = function(meal){
        ctrl.gotList = false;
        ctrl.shoppingList={};
        angular.forEach(meal.ingredients,function(ingredient,key){
            var product = {};
            //deep copy to avoid to customRecipe wich will be destroyed
            angular.copy(ingredient,product);
            ctrl.products.push(product);
        });
        ctrl.noproducts = false;
    };

    this.showShoppingList = function(){
        var uri = 'rs/shoppinglists'
        var parameter = JSON.stringify(ctrl.products);
        $http.post(uri,parameter)
                .success(function (ingredientList) {
                        ctrl.shoppinglist = ingredientList;
                        ctrl.noproducts = true;
                        ctrl.products = [];
                        ctrl.gotList = true;
                    })
                    .error(function(){
                        ctrl.alerts.push({type: 'danger', msg: 'Technical error'});
                    });
    }
}]);

app.controller('SelectionController', function(){
    this.selection = -1;
    this.customRecipe = {};
    this.originalRecipe = {};
    this.alerts = [];

    this.setSelection = function(recipe){
        this.selection = recipe.recipe_id;
        //deep copy because we don't want to modify original recipe !
        angular.copy(recipe,this.customRecipe);
        this.originalRecipe = recipe;
    };

    this.isSelected = function(recipeId){
        return recipeId === this.selection;
    };

    this.calcQuantity = function(customRecipe,originalRecipe){
        angular.forEach(this.customRecipe.ingredients,function(ingredient,key){
            //use originalQuantity or we become lost
            var originalQuantity = originalRecipe.ingredients[ingredient.display_index].quantity;
            ingredient.quantity = originalQuantity * customRecipe.servings / originalRecipe.servings;
        });

        if (customRecipe.servings !== originalRecipe.servings){
            this.alerts = [];
            var message = "La liste d'ingrédients est prévue pour "+ customRecipe.servings + " convives. Cependant les instructions ci-dessus correspondent à une préparation pour "
            + originalRecipe.servings + " convives. Pensez à bien adjuster le temps, la température et les quantités selon le besoin."
            this.alerts.push({type: 'danger', msg: message});
        }
        else {
            this.alerts = [];
        }
    };
});


