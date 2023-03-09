package persistence;

import model.FoodIntake;
import model.FoodList;
import model.User;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user information from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User readUser() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads foodList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodList readFoodList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //MODIFIES: User
    //EFFECTS: parses user information from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        double age = jsonObject.optDouble("age");
        double bodyWeight = jsonObject.getDouble("bodyWeight");
        double height = jsonObject.getDouble("height");
        User.Gender gender = User.Gender.valueOf(jsonObject.getString("gender"));
        User.ActiveLevel activeLevel = User.ActiveLevel.valueOf(jsonObject.getString("activeLevel"));
        User user = new User(age, bodyWeight, height, gender, activeLevel);
        return user;
    }



    //EFFECTS: parses foodList from JSON object and returns it
    private FoodList parseFoodList(JSONObject jsonObject) {
        FoodList fl = new FoodList();
        addFoodies(fl, jsonObject);
        return fl;
    }

    // MODIFIES: FoodList
    // EFFECTS: parses arbitrary food from JSON object and adds them to foodList
    private void addFoodies(FoodList fl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodList");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fl, nextFood);
        }
    }

    // MODIFIES: FoodList
    // EFFECTS: parses food from JSON object and adds it to food list
    private void addFood(FoodList fl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int cal = jsonObject.getInt("calories");
        int carbs = jsonObject.getInt("carbs");
        int fat = jsonObject.getInt("fat");
        int protein = jsonObject.getInt("protein");
        FoodIntake food = new FoodIntake(name, cal, carbs, fat, protein);
        fl.addFood(food);
    }
}

