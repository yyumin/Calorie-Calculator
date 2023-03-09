package ui;

import model.FoodIntake;
import model.FoodList;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FitnessAPP {
    private static final String JSON_STORE_USER = "./data/user.json";
    private static final String JSON_STORE = "./data/foodList.json";
    private Scanner input;
    private JsonWriter jsonWriterUserInfo;
    private JsonReader jsonReaderUserInfo;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private FoodList foodList1;
    private User user1;
    private int caloriesRemaining;

    // EFFECTS: run application
    public FitnessAPP() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriterUserInfo = new JsonWriter(JSON_STORE_USER);
        jsonReaderUserInfo = new JsonReader(JSON_STORE_USER);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAPP();
    }

    // CITATION: code from the sample application provided on edX.
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runAPP() throws FileNotFoundException {
        System.out.println("Do you want to input your user information? yes/no");
        String command = input.next();
        command = command.toLowerCase();
        boolean keepGoing = true;
        if (command.equals("yes")) {
            user1 = createNewUser();
        }
        foodList1 = new FoodList();

        while (keepGoing) {
            displayMenu();
            //printClientInfo();
            //user1.calculateTotalEnergyExpenditure();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // EFFECTS: constructs user information
    @SuppressWarnings("methodlength")
    public User createNewUser() {
        User.Gender gender1 = null;
        User.ActiveLevel activeLevel1 = null;
        System.out.println("Please enter your age:");
        double age1 = input.nextDouble();
        System.out.println("Please enter your weight in lbs");
        double bodyWeight1 = input.nextDouble();
        System.out.println("Please enter your height in inches");
        double height1 = input.nextDouble();
        String gender = "";
        while (!(gender.equals("female") || gender.equals("male"))) {
            System.out.println("Please enter your gender (FEMALE or MALE):");
            gender = input.next();
            if (gender.equalsIgnoreCase("FEMALE")) {
                gender1 = User.Gender.FEMALE;
            } else if (gender.equalsIgnoreCase("MALE")) {
                gender1 = User.Gender.MALE;
            }
        }
        System.out.println("What's your activity level?");
        System.out.println("s for Sedentary \n" + "l for Lightly active\n" + "m for Moderately active\n"
                + "v for Very active\n" + "e for Extra active\n");
        String activeLevel = "";
        while (!(activeLevel.equals("s") || activeLevel.equals("l") || activeLevel.equals("m")
                || activeLevel.equals("v") || activeLevel.equals("e"))) {
            System.out.println("What's your activity level?");
            activeLevel = input.next();
            if (activeLevel.equalsIgnoreCase("S")) {
                activeLevel1 = User.ActiveLevel.S;
            } else if (activeLevel.equalsIgnoreCase("L")) {
                activeLevel1 = User.ActiveLevel.L;
            } else if (activeLevel.equalsIgnoreCase("M")) {
                activeLevel1 = User.ActiveLevel.M;
            } else if (activeLevel.equalsIgnoreCase("V")) {
                activeLevel1 = User.ActiveLevel.V;
            } else {
                activeLevel1 = User.ActiveLevel.E;
            }
        }
        return new User(age1, bodyWeight1, height1, gender1, activeLevel1);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> check personal info");
        System.out.println("\ta -> add food");
        System.out.println("\tr -> remove food");
        System.out.println("\td -> display today's food consumption");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
            //user1.calculateTotalEnergyExpenditure();
        if ("c".equals(command)) {
            printClientInfo();
        } else if ("a".equals(command)) {
            addFood();
        } else if ("r".equals(command)) {
            deleteFood();
        } else if ("d".equals(command)) {
            displayFood();
        } else if (command.equals("s")) {
            saveUserInfo();
            saveFoodInfo();
        } else if (command.equals("l")) {
            loadUserInfo();
            loadFoodInfo();
        } else {
            System.out.println("Selection not valid...");
        }
    }



    private void addFood() throws  NullPointerException {
        try {
            FoodIntake food1 = createFood();
            foodList1.addFood(food1);
        } catch (NullPointerException e) {
            System.out.println("Please enter your personal information first.");
            user1 = createNewUser();
        }
    }

    private void deleteFood() throws NullPointerException {
        try {
            String name;
            input = new Scanner(System.in);
            System.out.println("Enter food name you wanna delete:");
            name = input.next();
            foodList1.removeFood(name);
        } catch (NullPointerException e) {
            System.out.println("Please enter your personal information first.");
            user1 = createNewUser();
        }
    }


    private FoodIntake createFood() throws NullPointerException {
        System.out.println("Please enter food name:");
        String name1 = input.next();
        int cal1 = 0;
        while (cal1 <= 0) {
            System.out.println("Please enter food calories:");
            cal1 = input.nextInt();
        }
        int carbs1 = 0;
        while (carbs1 <= 0) {
            System.out.println("Please enter food carb:");
            carbs1 = input.nextInt();
        }
        int fat1 = 0;
        while (fat1 <= 0) {
            System.out.println("Please enter food fat:");
            fat1 = input.nextInt();
        }
        int protein1 = 0;
        while (protein1 <= 0) {
            System.out.println("Please enter food protein:");
            protein1 = input.nextInt();
        }
        return new FoodIntake(name1, cal1, carbs1, fat1, protein1);
    }

    public void displayFood() throws NullPointerException {
        try {
            printFoodList();
            user1.calculateCaloriesRemaining(foodList1);
        } catch (NullPointerException e) {
            System.out.println("Please enter your personal information first.");
            user1 = createNewUser();
        }
    }

    public void printFoodList() throws  NullPointerException {
        for (int num = 0; num < foodList1.getFoodListSize(); num++) {
            System.out.println("Food: " + foodList1.getFood(num).getName());
            System.out.println("Calories: " + foodList1.getFood(num).getCal());
            System.out.println("Net Carbs: " + foodList1.getFood(num).getCarbs());
            System.out.println("Fat: " + foodList1.getFood(num).getFat());
            System.out.println("Protein: " + foodList1.getFood(num).getProtein());
        }
    }

    public void printClientInfo() throws NullPointerException {
        try {
            System.out.println("Age: " + user1.getAge());
            System.out.println("Weight: " + user1.getBodyWeight());
            System.out.println("Height: " + user1.getHeight());
            System.out.println("Gender: " + user1.getGender());
            System.out.println("Active Level: " + user1.getActiveLevel());
        } catch (NullPointerException e) {
            System.out.println("Please enter your personal information first.");
            user1 = createNewUser();
        }
    }

    private void saveUserInfo() {
        try {
            jsonWriterUserInfo.open();
            jsonWriterUserInfo.writeUser(user1);
            jsonWriterUserInfo.close();
            System.out.println("Saved your info " + " to " + JSON_STORE_USER);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_USER);
        }
    }

    // EFFECTS: saves the foodList to file
    private void saveFoodInfo() {
        try {
            jsonWriter.open();
            jsonWriter.writeFoodList(foodList1);
            jsonWriter.close();
            System.out.println("Saved your daily consumed calories " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads information from file
    private void loadUserInfo() {
        try {
            user1 = jsonReaderUserInfo.readUser();
            System.out.println("Loaded your info from " + JSON_STORE_USER);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_USER);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads information from file
    private void loadFoodInfo() {
        try {
            foodList1 = jsonReader.readFoodList();
            System.out.println("Loaded your daily consumed calories from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
