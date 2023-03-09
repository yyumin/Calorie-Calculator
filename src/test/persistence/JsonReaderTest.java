package persistence;

import model.FoodList;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static model.User.ActiveLevel.M;
import static model.User.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFoodListFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodList fl = reader.readFoodList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentUser() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.readUser();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFoodList.json");
        try {
            FoodList fl = reader.readFoodList();
            assertEquals(0, fl.getFoodListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodList.json");
        try {
            FoodList fl = reader.readFoodList();
            assertEquals(2, fl.getFoodListSize());
            checkFood("eggs", 100,10,10,10, fl.getFood(0));
            checkFood("banana", 300, 10, 10, 10, fl.getFood(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.readUser();
            assertEquals(63.2, user.getHeight());
            checkUser(26, 143.1, 63.2, FEMALE, M,user);
            assertEquals(63.2,user.getHeight());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}