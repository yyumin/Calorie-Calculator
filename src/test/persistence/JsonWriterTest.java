package persistence;

import model.FoodIntake;
import model.FoodList;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static model.User.ActiveLevel.M;
import static model.User.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvaildFoodListFile() {
        try {
            FoodList fl = new FoodList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvaildUserFile() {
        try {
            User user = new User("MAX",26, 143.1, 63.2, FEMALE, M);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFoodList() {
        try {
            FoodList fl = new FoodList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodList.json");
            writer.open();
            writer.writeFoodList(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodList.json");
            fl = reader.readFoodList();
            assertEquals(0, fl.getFoodListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodList() {
        try {
            FoodList fl = new FoodList();
            fl.addFood(new FoodIntake("eggs", 100, 10, 10, 10));
            fl.addFood(new FoodIntake("banana", 300, 10, 10, 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodList.json");
            writer.open();
            writer.writeFoodList(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodList.json");
            fl = reader.readFoodList();
            assertEquals(2, fl.getFoodListSize());
            checkFood("eggs", 100, 10, 10, 10, fl.getFood(0));
            checkFood("banana", 300, 10, 10, 10, fl.getFood(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("Max",26, 143.1, 63.2, FEMALE, M);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.writeUser(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.readUser();
            checkUser(26, 143.1, 63.2, FEMALE, M,user);
            assertEquals(63.2, user.getHeight());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
