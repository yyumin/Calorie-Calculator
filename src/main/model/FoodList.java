package model;

import java.util.ArrayList;

// Represents a collection of food of today
public class FoodList {
    private final ArrayList<FoodIntake> foodList;
    private int originalCalories = 0;

    /*
     * EFFECTS:  constructs new food list with food inside
     */
    public FoodList() {
        foodList = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: add food into the food collection
     */
    public void addFood(FoodIntake food) {
        foodList.add(food);
    }

    /*
     * MODIFIES: this
     * EFFECTS: if no food inside the list produce false
     *          food is deleted from collection and produce true
     */
    public boolean removeFood(String foodName) {
        for (FoodIntake food : foodList) {
            if (food.getName().equals(foodName)) {
                foodList.remove(food);
                originalCalories = originalCalories - food.getCal();
                return true;
            }
        }
        return false;
    }


    /*
     * REQUIRES: food list is not empty
     * MODIFIES: this
     * EFFECTS: if food list is empty, produce zero; if there is food inside the list, add and produce
     *          the total calories of the food
     */
    public int totalCalories() {
        if (foodList.size() != 0) {
            for (FoodIntake food : foodList) {
                originalCalories += food.getCal();
            }
        }
        return originalCalories;
    }

    public FoodIntake getFood(int index) {
        return foodList.get(index);
    }

    public int getFoodListSize() {
        return foodList.size();
    }

    public int getOriginalCalories() {
        return originalCalories;
    }

}
