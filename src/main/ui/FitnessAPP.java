package ui;

import model.FoodIntake;
import model.FoodList;
import model.User;

import java.util.Scanner;

public class FitnessAPP {
    private User user1;
    private FoodList foodList1;
    private Scanner input;


    public FitnessAPP() {
        runAPP();
    }

    public void runAPP() {
        boolean keepGoing = true;
        String command = null;
        user1 = createNewUser();
        foodList1 = new FoodList();
        user1.print();
        calculate();


        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    public User createNewUser() {
        input = new Scanner(System.in);
        System.out.println("Please enter your age:");
        double age1 = input.nextDouble();
        System.out.println("Please enter your weight in lbs");
        double bodyWeight1 = input.nextDouble();
        System.out.println("Please enter your height in inches");
        double height1 = input.nextDouble();
        String gender1 = "";
        while (!(gender1.equals("female") || gender1.equals("male"))) {
            System.out.println("Please enter your gender (female or male):");
            gender1 = input.next();
            gender1 = gender1.toLowerCase();
        }
        String activeLevel1 = getActiveLevel();
        return new User(age1, bodyWeight1, height1, gender1, activeLevel1);
    }

    public String getActiveLevel() {
        input = new Scanner(System.in);
        System.out.println("What's your activity level?");
        System.out.println("s for Sedentary \n" + "l for Lightly active\n" + "m for Moderately active\n"
                + "v for Very active\n" + "e for Extra active\n");
        String activeLevel = "";
        while (!(activeLevel.equals("s") || activeLevel.equals("l") || activeLevel.equals("m")
                || activeLevel.equals("v") || activeLevel.equals("e"))) {
            System.out.println("What's your activity level?");
            activeLevel = input.next();
            activeLevel = activeLevel.toLowerCase();
        }
        return activeLevel;
    }

    public void calculate() {
        calculateBMR(user1);
        calculateTotalEnergyExpenditure(user1);
    }

    public int calculateBMR(User user) {
        int bmr = 0;
        if (user.getGender().equals("female")) {
            bmr = user.calculateMetabolismFemale();
        } else if (user.getGender().equals("male")) {
            bmr = user.calculateMetabolismMale();
        } else {
            System.out.println("Please write down the right gender");
        }
        return bmr;
    }

    public int calculateTotalEnergyExpenditure(User user) {
        int t = 0;
        switch (user.getActiveLevel()) {
            case "s":
                t = (int) (calculateBMR(user) * 1.2);
                System.out.println("Your activity factor is 1.2 and your Total Daily Energy Expenditure is: " + t);
                break;
            case "l":
                t = (int) (calculateBMR(user) * 1.375);
                System.out.println("your total daily energy expenditure is: " + t);
                break;
            case "m":
                t = (int) (calculateBMR(user) * 1.55);
                System.out.println("your total daily energy expenditure is: " + t);
                break;
            case "v":
                t = (int) (calculateBMR(user) * 1.725);
                System.out.println("your total daily energy expenditure is: " + t);
                break;
            case "e":
                t = (int) (calculateBMR(user) * 1.9);
                System.out.println("your total daily energy expenditure is: " + t);
                break;
        }
        return t;
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> check personal info");
        System.out.println("\ta -> add food");
        System.out.println("\tr -> remove food");
        System.out.println("\td -> display today's food consumption");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if ("c".equals(command)) {
            user1.print();
            calculate();
        } else if ("a".equals(command)) {
            addFood();
        } else if ("r".equals(command)) {
            deleteFood();
        } else if ("d".equals(command)) {
            foodList1.displayFood();
            remainingCalories();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void addFood() {
        FoodIntake food1 = createFood();
        foodList1.addFood(food1);
    }

    private void deleteFood() {
        String name;
        input = new Scanner(System.in);
        System.out.println("Enter food name you wanna delete:");
        name = input.next();
        foodList1.removeFood(name);

    }


    private FoodIntake createFood() {
        System.out.println("Enter food name:");
        String name1 = input.next();
        int cal1 = 0;
        while (cal1 <= 0) {
            System.out.println("Enter food calories:");
            cal1 = input.nextInt();
        }
        int carbs1 = 0;
        while (carbs1 <= 0) {
            System.out.println("Enter food carb:");
            carbs1 = input.nextInt();
        }
        int fat1 = 0;
        while (fat1 <= 0) {
            System.out.println("Enter food fat:");
            fat1 = input.nextInt();
        }
        int protein1 = 0;
        while (protein1 <= 0) {
            System.out.println("Enter food protein:");
            protein1 = input.nextInt();
        }
        return new FoodIntake(name1, cal1, carbs1, fat1, protein1);
    }

    public void remainingCalories() {
        int dailyTotal = calculateTotalEnergyExpenditure(user1);
        int listTotal = foodList1.totalCalories();
        int caloriesRemaining = dailyTotal - listTotal;
        System.out.println("Your daily calories remaining is: " + caloriesRemaining);
    }

}
