package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a collection of food of today
public class FoodList implements Writable {
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
        EventLog.getInstance().logEvent(new Event(food.getName() + " added to the Food Bank"));
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
                EventLog.getInstance().logEvent(new Event(foodName + " removed from the Food Bank"));
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("totalDailyCalories", getTotalDailyCalories());
        json.put("foodList", foodListToJson());
        return json;
    }

    private JSONArray foodListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodIntake f : foodList) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

}
