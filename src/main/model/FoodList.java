package model;

import java.util.ArrayList;

// Represents a collection of food of today
public class FoodList {
    private final ArrayList<FoodIntake> foodList;
    private int totalDailyCalories = 0; //total daily calories of the client

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
        totalDailyCalories = totalDailyCalories - food.getCal();
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
                totalDailyCalories = totalDailyCalories + food.getCal();
                return true;
            }
        }
        return false;
    }


    public FoodIntake getFood(int index) {
        return foodList.get(index);
    }

    public int getFoodListSize() {
        return foodList.size();
    }

    public int getTotalDailyCalories() {
        return totalDailyCalories;
    }

}
