package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User testUser1;
    private User testUser2;

    @BeforeEach
    void setup() {
        testUser1 = new User(26, 143.1, 63.2, "female", "l");
        testUser2 = new User(26, 143.1, 63.2, "male", "e");
    }

    @Test
    void testConstructor() {
        assertEquals(26, testUser1.getAge());
        assertEquals(143.1, testUser1.getBodyWeight());
        assertEquals(63.2, testUser1.getHeight());
        assertEquals("l", testUser1.getActiveLevel());
        assertEquals("female", testUser1.getGender());
    }

    @Test
    void testCalculateMetabolismFemale() {
        assertEquals(1452, testUser1.calculateMetabolismFemale());
    }

    @Test
    void testCalculateMetabolismMale() {
        assertEquals(1586, testUser2.calculateMetabolismMale());
    }


}