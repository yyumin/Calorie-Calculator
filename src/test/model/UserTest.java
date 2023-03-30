package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.User.ActiveLevel.*;
import static model.User.Gender.FEMALE;
import static model.User.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class UserTest {
    private User testUser1;
    private User testUser2;
    private User testUser3;
    private User testUser4;
    private User testUser5;
    private User testUser6;
    private User testUser7;


    @BeforeEach
    void setup() {
        testUser1 = new User("Max", 26, 143.1, 63.2, FEMALE, M);
        testUser2 = new User("Max",26, 143.1, 63.2, MALE, E);
        testUser3 = new User("Max",26, 143.1, 63.2, MALE, S);
        testUser4 = new User("Max",26, 143.1, 63.2, MALE, L);
        testUser5 = new User("Max",26, 143.1, 63.2, MALE, V);
        testUser6 = new User("Max",26, 143.1, 63.2, null, M);
        testUser7 = new User("Max",26, 143.1, 63.2, FEMALE, null);

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
    void testCalculateMetabolismNull() {
        assertEquals(0, testUser6.calculateMetabolism());
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
        assertEquals(3013,testUser2.calculateTotalEnergyExpenditure());
        assertEquals(1903,testUser3.calculateTotalEnergyExpenditure());
        assertEquals(2180,testUser4.calculateTotalEnergyExpenditure());
        assertEquals(2735,testUser5.calculateTotalEnergyExpenditure());
    }

    @Test
    void testCalculateTotalEnergyExpenditureNull() {
        FoodList foodList = new FoodList();
        FoodIntake food1 = new FoodIntake("eggs", 100, 10, 10, 10);
        FoodIntake food2 = new FoodIntake("bread", 200, 20, 20, 20);
        FoodIntake food3 = new FoodIntake("salmon", 300, 10, 10, 10);
        foodList.addFood(food1);
        foodList.addFood(food2);
        foodList.addFood(food3);
        assertEquals(0, testUser7.calculateTotalEnergyExpenditure());
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