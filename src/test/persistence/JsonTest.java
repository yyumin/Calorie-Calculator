package persistence;

import model.FoodIntake;
import model.FoodList;
import model.User;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String name, int cal, int carbs, int fat, int protein, FoodIntake foodIntake) {
        assertEquals(name, foodIntake.getName());
        assertEquals(cal,foodIntake.getCal());
        assertEquals(carbs,foodIntake.getCarbs());
        assertEquals(fat,foodIntake.getFat());
        assertEquals(protein,foodIntake.getProtein());
    }

    protected void checkUser(int age, int bodyWeight, int height, User.Gender g, User.ActiveLevel a, User user) {
        assertEquals(age,user.getAge());
        assertEquals(bodyWeight,user.getBodyWeight());
        assertEquals(height,user.getHeight());
        assertEquals(g, user.getGender());
        assertEquals(a,user.getActiveLevel());
    }
}
