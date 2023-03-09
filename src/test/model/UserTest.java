package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.User.ActiveLevel.*;
import static model.User.Gender.FEMALE;
import static model.User.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User testUser1;
    private User testUser2;

    @BeforeEach
    void setup() {
        testUser1 = new User(26, 143.1, 63.2, FEMALE, M);
        testUser2 = new User(26, 143.1, 63.2, MALE, E);
    }

    @Test
    void testConstructor() {
        assertEquals(26, testUser1.getAge());
        assertEquals(143.1, testUser1.getBodyWeight());
        assertEquals(63.2, testUser1.getHeight());
        assertEquals(M, testUser1.getActiveLevel());
        assertEquals(FEMALE, testUser1.getGender());
    }

    @Test
    void testCalculateMetabolismFemale() {
        assertEquals(1452, testUser1.calculateMetabolism());
    }

    @Test
    void testCalculateMetabolismMale() {
        assertEquals(1586, testUser2.calculateMetabolism());
    }

    @Test
    void testCalculateTotalEnergyExpenditure() {
        FoodList foodList = new FoodList();
        FoodIntake food1 = new FoodIntake("eggs", 100, 10, 10, 10);
        FoodIntake food2 = new FoodIntake("bread", 200, 20, 20, 20);
        FoodIntake food3 = new FoodIntake("salmon", 300, 10, 10, 10);
        foodList.addFood(food1);
        foodList.addFood(food2);
        foodList.addFood(food3);
        assertEquals(2250,testUser1.calculateTotalEnergyExpenditure());
    }

    @Test
    void testCalculateCaloriesRemaining() {
        FoodList foodList = new FoodList();
        FoodIntake food1 = new FoodIntake("eggs", 100, 10, 10, 10);
        FoodIntake food2 = new FoodIntake("bread", 200, 20, 20, 20);
        FoodIntake food3 = new FoodIntake("salmon", 300, 10, 10, 10);
        foodList.addFood(food1);
        foodList.addFood(food2);
        foodList.addFood(food3);
        assertEquals(1650,testUser1.calculateCaloriesRemaining(foodList));
    }
}