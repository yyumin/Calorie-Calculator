package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodListTest {

    private FoodList testFoodList;
    private FoodIntake food1;
    private FoodIntake food2;
    private FoodIntake food3;

    @BeforeEach
    void setup() {
        testFoodList = new FoodList();
        food1 = new FoodIntake("eggs", 100, 10, 10, 10);
        food2 = new FoodIntake("bread", 200, 20, 20, 20);
        food3 = new FoodIntake("salmon", 300, 10, 10, 10);
    }

    @Test
    public void testAddFood() {
        testFoodList.addFood(food1);
        testFoodList.addFood(food2);
        testFoodList.addFood(food3);
        assertEquals(food1, testFoodList.getFood(0));
        assertEquals(food2, testFoodList.getFood(1));
        assertEquals(food3, testFoodList.getFood(2));
    }

    @Test
    public void testRemoveFoodEmpty() {
        assertFalse(testFoodList.removeFood("eggs"));
    }

    @Test
    public void testRemoveFirstFood() {
        testFoodList.addFood(food1);
        assertEquals(-100, testFoodList.getTotalDailyCalories());
        assertTrue(testFoodList.removeFood("eggs"));
        assertEquals(0, testFoodList.getFoodListSize());
        assertEquals(0, testFoodList.getTotalDailyCalories());
    }

    @Test
    public void testRemoveNotFirstFood() {
        testFoodList.addFood(food1);
        testFoodList.addFood(food2);
        assertEquals(-300, testFoodList.getTotalDailyCalories());
        assertTrue(testFoodList.removeFood("bread"));
        assertEquals(1, testFoodList.getFoodListSize());
        assertEquals(-100, testFoodList.getTotalDailyCalories());

    }

    @Test
    public void testEmptyFoodListCalories() {
        assertEquals(0, testFoodList.getTotalDailyCalories());
    }

    @Test
    public void testFoodListTotalCalories() {
        testFoodList.addFood(food1);
        testFoodList.addFood(food2);
        testFoodList.addFood(food3);
        assertEquals(-600, testFoodList.getTotalDailyCalories());
    }
}
