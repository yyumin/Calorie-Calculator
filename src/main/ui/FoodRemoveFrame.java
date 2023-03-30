package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

// represents the food removing window
public class FoodRemoveFrame extends JFrame {
    private final JPanel foodRemovePanel;
    private final FitnessAPP fitnessAPP;

    private JLabel nameLabel;
    private JTextField foodName;
    private JButton enterButton;

    // EFFECTS: creates food removing window
    public FoodRemoveFrame(FitnessAPP fitnessAPP) {
        this.setName("Food Bank Display");
        this.setSize(400, 400);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null); //position the frame in the center of the screen

        foodRemovePanel = new JPanel();
        foodRemovePanel.setBackground(Color.WHITE);
        foodRemovePanel.setLayout(new BoxLayout(foodRemovePanel, BoxLayout.Y_AXIS));

        this.add(foodRemovePanel);
        this.setVisible(true);
        this.setResizable(true);

        this.fitnessAPP = fitnessAPP;
    }


    // EFFECTS: Create and set up the Food Remove window and delete a selected food from food bank
    public void removeFood() {
        nameLabel = new JLabel("Type in food name that you wanna remove:");
        foodRemovePanel.add(nameLabel);
        foodRemovePanel.add(Box.createVerticalStrut(10));

        foodName = new JTextField(1);
        foodName.setMaximumSize(new Dimension(200, 25));
        foodRemovePanel.add(foodName);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            String foodNameBreakdown = foodName.getText();
            getFitnessAPP().getFoodList().removeFood(foodNameBreakdown);
            JLabel totalCaloriesRemainingBreakdown = new JLabel("Your Total Remaining Calories are: "
                    + getFitnessAPP().getUser().getCaloriesRemaining());
            foodRemovePanel.add(totalCaloriesRemainingBreakdown);
        });
        foodRemovePanel.add(enterButton);

    }

    public FitnessAPP getFitnessAPP() {
        return fitnessAPP;
    }
}
