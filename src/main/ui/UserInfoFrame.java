package ui;

import javax.swing.*;
import java.awt.*;

// represents the user info popup window
public class UserInfoFrame extends JFrame {
    private final JPanel userInfoPanel;
    private final FitnessAppGUI fitnessAppGUI;

    public UserInfoFrame(FitnessAppGUI fitnessAppGUI) {
        this.setName("User Info");
        this.setSize(400, 400);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null); //position the frame in the center of the screen

        userInfoPanel = new JPanel();
        userInfoPanel.setBackground(Color.WHITE);
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        this.add(userInfoPanel);
        this.setVisible(true);
        this.setResizable(true);

        this.fitnessAppGUI = fitnessAppGUI;
    }

    // EFFECTS: creates user display window
    public void displayClientInfo() {
        JLabel userInfoBreakdown = new JLabel("Your personal information:");
        JLabel nameBreakdown = new JLabel("Name:" + getFitnessAPP().getUser().getName());
        JLabel ageBreakdown = new JLabel("Age:" + getFitnessAPP().getUser().getAge());
        JLabel weightBreakdown = new JLabel("Weight:" + getFitnessAPP().getUser().getBodyWeight());
        JLabel heightBreakdown = new JLabel("Height:" + getFitnessAPP().getUser().getHeight());
        JLabel genderBreakdown = new JLabel("Gender:" + getFitnessAPP().getUser().getGender());
        JLabel activeLevelBreakdown = new JLabel("Active Level:" + getFitnessAPP().getUser().getActiveLevel());
        JLabel totalEnergyExpenditureBreakdown = new JLabel("Your Total Daily Energy Expenditure is: "
                + getFitnessAPP().getUser().getTotalEnergyExpenditure());


        userInfoPanel.add(Box.createVerticalStrut(20));
        userInfoPanel.add(userInfoBreakdown);
        userInfoPanel.add(nameBreakdown);
        userInfoPanel.add(ageBreakdown);
        userInfoPanel.add(weightBreakdown);
        userInfoPanel.add(heightBreakdown);
        userInfoPanel.add(genderBreakdown);
        userInfoPanel.add(activeLevelBreakdown);
        userInfoPanel.add(totalEnergyExpenditureBreakdown);
    }

    public FitnessAppGUI getFitnessAPP() {
        return fitnessAppGUI;
    }
}
