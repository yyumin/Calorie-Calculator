package ui;

import model.FoodIntake;

import javax.swing.*;
import java.awt.*;

// represents the new food creation popup window
public class FoodCreateFrame extends JFrame {
    private final JPanel foodCreatePanel;
    private final FitnessAppGUI fitnessAppGUI;

    private JLabel nameLabel;
    private JLabel caloriesLabel;
    private JLabel carbLabel;
    private JLabel fatLabel;
    private JLabel proteinLabel;

    private JTextField foodName;
    private JTextField foodCalories;
    private JTextField foodCarb;
    private JTextField foodFat;
    private JTextField foodProtein;

    private JButton enterButton;

    // EFFECTS: creates new food creation window
    public FoodCreateFrame(FitnessAppGUI fitnessAppGUI) {
        this.setName("Food Intake");
        this.setSize(400, 600);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null); //position the frame in the center of the screen

        foodCreatePanel = new JPanel();
        foodCreatePanel.setBackground(Color.WHITE);
        foodCreatePanel.setLayout(new BoxLayout(foodCreatePanel,FlowLayout.CENTER));

        this.add(foodCreatePanel);
        this.setVisible(true);
        this.setResizable(true);

        this.fitnessAppGUI = fitnessAppGUI;
    }

    // MODIFIES: this
    // EFFECTS: constructs user info frame and panel, prompts user to enter name and basic info
    @SuppressWarnings("methodlength")
    public void runFoodCreatFrame() {
        nameLabel = new JLabel("Type in food name:");
        foodCreatePanel.add(nameLabel);
        foodCreatePanel.add(Box.createVerticalStrut(10));
        foodName = new JTextField(1);
        foodName.setMaximumSize(new Dimension(200, 25));
        foodCreatePanel.add(foodName);

        caloriesLabel = new JLabel("Type in food calories:");
        foodCreatePanel.add(caloriesLabel);
        foodCreatePanel.add(Box.createVerticalStrut(10));
        foodCalories = new JTextField(1);
        foodCalories.setMaximumSize(new Dimension(200, 25));
        foodCreatePanel.add(foodCalories);

        carbLabel = new JLabel("Type in food carbs:");
        foodCreatePanel.add(carbLabel);
        foodCreatePanel.add(Box.createVerticalStrut(10));
        foodCarb = new JTextField(1);
        foodCarb.setMaximumSize(new Dimension(200, 25));
        foodCreatePanel.add(foodCarb);

        fatLabel = new JLabel("Type in food fat:");
        foodCreatePanel.add(fatLabel);
        foodCreatePanel.add(Box.createVerticalStrut(10));
        foodFat = new JTextField(1);
        foodFat.setMaximumSize(new Dimension(200, 25));
        foodCreatePanel.add(foodFat);

        proteinLabel = new JLabel("Type in food protein:");
        foodCreatePanel.add(proteinLabel);
        foodCreatePanel.add(Box.createVerticalStrut(10));
        foodProtein = new JTextField(1);
        foodProtein.setMaximumSize(new Dimension(200, 25));
        foodCreatePanel.add(foodProtein);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            processFoodIntake();
        });
        foodCreatePanel.add(enterButton);

        foodCreatePanel.setVisible(true);
        this.setVisible(true);
    }

    // EFFECTS: add food into the food bank
    private void processFoodIntake() {
        String name = foodName.getText();
        int calories = Integer.parseInt(foodCalories.getText());
        int carb = Integer.parseInt(foodCarb.getText());
        int fat = Integer.parseInt(foodFat.getText());
        int protein = Integer.parseInt(foodProtein.getText());
        FoodIntake food = new FoodIntake(name,calories,carb,fat,protein);
        fitnessAppGUI.getFoodList().addFood(food);
    }

    public FitnessAppGUI getFitnessAPP() {
        return fitnessAppGUI;
    }
}
