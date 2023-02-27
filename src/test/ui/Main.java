package ui;

import model.FoodIntake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodIntakeTest {
    private FoodIntake testFoodIntake;

    @BeforeEach
    void setup() {
        testFoodIntake = new FoodIntake("eggs", 100, 10, 10, 10);
    }

    @Test
    void testConstructor() {
        assertEquals("eggs", testFoodIntake.getName());
        assertEquals(100, testFoodIntake.getCal());
        assertEquals(10, testFoodIntake.getCarbs());
        assertEquals(10, testFoodIntake.getFat());
        assertEquals(10, testFoodIntake.getProtein());
    }

}