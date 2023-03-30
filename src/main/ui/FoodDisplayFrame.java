package ui;

import javax.swing.*;
import java.awt.*;

// represents the food bank consumption window
public class FoodDisplayFrame extends JFrame {
    private final JPanel foodDisplayPanel;

    private final FitnessAPP fitnessAPP;

    private JLabel foodIntakeLabel;
    private final JLabel printLabel = new JLabel();
    private final JButton removeBottom = new JButton("Remove");

    public FoodDisplayFrame(FitnessAPP fitnessAPP) {
        this.setName("Food Bank Display");
        this.setSize(400, 400);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null); //position the frame in the center of the screen

        foodDisplayPanel = new JPanel();
        foodDisplayPanel.setBackground(Color.WHITE);
        foodDisplayPanel.setLayout(new BoxLayout(foodDisplayPanel, BoxLayout.Y_AXIS));

        this.add(foodDisplayPanel);
        this.setVisible(true);
        this.setResizable(true);

        this.fitnessAPP = fitnessAPP;
    }


    public void displayFoodList() {
        printLabel.setText("Here is your food consumption list:");
        foodDisplayPanel.add(printLabel);
        for (int num = 0; num < getFitnessAPP().getFoodList().getFoodListSize(); num++) {
            String s = "<html>";
            s += "<br/>Food: " + getFitnessAPP().getFoodList().getFood(num).getName();
            s += "<br/>Calories: " + getFitnessAPP().getFoodList().getFood(num).getCal();
            s += "<br/>Net Carbs: " + getFitnessAPP().getFoodList().getFood(num).getCarbs();
            s += "<br/>Fat: " + getFitnessAPP().getFoodList().getFood(num).getFat();
            s += "<br/>Protein: " + getFitnessAPP().getFoodList().getFood(num).getProtein();
            foodIntakeLabel = new JLabel(s);
            foodDisplayPanel.add(foodIntakeLabel);
        }
        getFitnessAPP().getUser().calculateCaloriesRemaining(getFitnessAPP().getFoodList());
        JLabel caloriesRemainingBreakdown = new JLabel("Your daily calories remaining is: "
                + getFitnessAPP().getUser().getCaloriesRemaining());
        foodDisplayPanel.add(caloriesRemainingBreakdown);

    }

    public FitnessAPP getFitnessAPP() {
        return fitnessAPP;
    }
}
