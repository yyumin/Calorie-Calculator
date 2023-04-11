package ui;

import model.User;

import javax.swing.*;
import java.awt.*;

import static model.User.ActiveLevel.*;
import static model.User.Gender.FEMALE;
import static model.User.Gender.MALE;

// represents the new user creation popup window
public class UserCreateFrame extends JFrame {

    private final JPanel userCreatePanel;
    private final FitnessAppGUI fitnessAppGUI;

    private JLabel welcomeLabel;
    private JLabel ageLabel;
    private JLabel weightLabel;
    private JLabel heightLabel;
    private JLabel genderLabel;
    private JLabel activeLevelLabel;

    private JTextField clientName;
    private JTextField clientAge;
    private JTextField clientWeight;
    private JTextField clientHeight;
    private JTextField clientGender;
    private JTextField clientActiveLevel;

    private JButton enterButton;

    // EFFECTS: creates new user creation window
    public UserCreateFrame(FitnessAppGUI fitnessAppGUI) {
        this.setName("User Info Collection");
        this.setSize(400, 650);
        this.setLocationRelativeTo(null); //position the frame in the center of the screen

        userCreatePanel = new JPanel();
        userCreatePanel.setLayout(new BoxLayout(userCreatePanel,FlowLayout.CENTER));

        this.add(userCreatePanel);
        this.setVisible(true);
        this.setResizable(true);

        this.fitnessAppGUI = fitnessAppGUI;
    }

    // MODIFIES: this
    // EFFECTS: constructs user info frame and panel, prompts user to enter name and basic info
    @SuppressWarnings("methodlength")
    public void runUserInfoFrame() {
        welcomeLabel = new JLabel("Type in your name:");
        userCreatePanel.add(welcomeLabel);
        userCreatePanel.add(Box.createVerticalStrut(10));
        clientName = new JTextField(1);
        clientName.setMaximumSize(new Dimension(200, 25));
        userCreatePanel.add(clientName);

        ageLabel = new JLabel("Type in your age:");
        userCreatePanel.add(ageLabel);
        userCreatePanel.add(Box.createVerticalStrut(10));
        clientAge = new JTextField(1);
        clientAge.setMaximumSize(new Dimension(200, 25));
        userCreatePanel.add(clientAge);

        weightLabel = new JLabel("Type in your weight in lbs:");
        userCreatePanel.add(weightLabel);
        userCreatePanel.add(Box.createVerticalStrut(10));
        clientWeight = new JTextField(1);
        clientWeight.setMaximumSize(new Dimension(200, 25));
        userCreatePanel.add(clientWeight);

        heightLabel = new JLabel("Type in your height in inches:");
        userCreatePanel.add(heightLabel);
        userCreatePanel.add(Box.createVerticalStrut(10));
        clientHeight = new JTextField(1);
        clientHeight.setMaximumSize(new Dimension(200, 25));
        userCreatePanel.add(clientHeight);

        createGender();
        createActiveLevel();

        enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            processClientInfo();
        });
        userCreatePanel.add(enterButton);

        userCreatePanel.setVisible(true);
        this.setVisible(true);
    }

    // EFFECTS: constructs user info frame and panel, prompts user to enter basic info and instantiate User
    @SuppressWarnings("methodlength")
    private void processClientInfo() {
        String name = clientName.getText();
        Double age = Double.parseDouble(clientAge.getText());
        Double bodyWeight = Double.parseDouble(clientWeight.getText());
        Double height = Double.parseDouble(clientHeight.getText());

        String command1 = clientGender.getText().toUpperCase();
        while (!(command1.equals("FEMALE") || command1.equals("MALE"))) {
            JLabel mustBeForM = new JLabel("Enter your gender");
            userCreatePanel.add(mustBeForM);
            mustBeForM.setVisible(true);
            createGender();
        }
        User.Gender gender = null;
        if (command1.equalsIgnoreCase("FEMALE")) {
            gender = FEMALE;
        } else if (command1.equalsIgnoreCase("MALE")) {
            gender = MALE;
        }

        String command2 = clientActiveLevel.getText().toLowerCase();
        while (!(command2.equals("s") || command2.equals("l") || command2.equals("m")
                || command2.equals("v") || command2.equals("e"))) {
            JLabel mustBeTheseChar = new JLabel("Enter the active level provided");
            userCreatePanel.add(mustBeTheseChar);
            mustBeTheseChar.setVisible(true);
            createActiveLevel();
        }
        User.ActiveLevel activeLevel = null;
        if (command2.equalsIgnoreCase("S")) {
            activeLevel = S;
        } else if (command2.equalsIgnoreCase("L")) {
            activeLevel = L;
        } else if (command2.equalsIgnoreCase("M")) {
            activeLevel = M;
        } else if (command2.equalsIgnoreCase("V")) {
            activeLevel = V;
        } else {
            activeLevel = E;
        }
        fitnessAppGUI.setUser(new User(name, age, bodyWeight, height, gender, activeLevel));
        fitnessAppGUI.getUser().calculateTotalEnergyExpenditure();
        displayClientInfo();
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter their gender
    private void createGender() {
        genderLabel = new JLabel("Type in your gender (FEMALE or MALE):");
        userCreatePanel.add(genderLabel);
        userCreatePanel.add(Box.createVerticalStrut(10));
        clientGender = new JTextField(1);
        clientGender.setMaximumSize(new Dimension(200, 25));
        userCreatePanel.add(clientGender);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter their active level
    private void createActiveLevel() {
        String s = "<html>";
        s += "<br/>" + "Type in your active level: ";
        s += "<br/> ";
        s += "<br/>S for Sedentary ";
        s += "<br/>L for Lightly active ";
        s += "<br/>M for Moderately active ";
        s += "<br/>V for Very active ";
        s += "<br/>E for Extra active ";

        activeLevelLabel = new JLabel(s);

        userCreatePanel.add(activeLevelLabel);
        userCreatePanel.add(Box.createVerticalStrut(20));
        clientActiveLevel = new JTextField(1);
        clientActiveLevel.setMaximumSize(new Dimension(200, 25));
        userCreatePanel.add(clientActiveLevel);
    }

    // EFFECTS: prints user info
    private void displayClientInfo() {
        enterButton.setVisible(false);
        JLabel userInfoBreakdown = new JLabel("Your personal information:");
        JLabel nameBreakdown = new JLabel("Name:" + getFitnessAPP().getUser().getName());
        JLabel ageBreakdown = new JLabel("Age:" + getFitnessAPP().getUser().getAge());
        JLabel weightBreakdown = new JLabel("Weight:" + getFitnessAPP().getUser().getBodyWeight());
        JLabel heightBreakdown = new JLabel("Height:" + getFitnessAPP().getUser().getHeight());
        JLabel genderBreakdown = new JLabel("Gender:" + getFitnessAPP().getUser().getGender());
        JLabel activeLevelBreakdown = new JLabel("Active Level:" + getFitnessAPP().getUser().getActiveLevel());
        JLabel totalEnergyExpenditureBreakdown = new JLabel("Your Total Daily Energy Expenditure is: "
                + getFitnessAPP().getUser().getTotalEnergyExpenditure());

        userCreatePanel.add(Box.createVerticalStrut(20));
        userCreatePanel.add(userInfoBreakdown);
        userCreatePanel.add(nameBreakdown);
        userCreatePanel.add(ageBreakdown);
        userCreatePanel.add(weightBreakdown);
        userCreatePanel.add(heightBreakdown);
        userCreatePanel.add(genderBreakdown);
        userCreatePanel.add(activeLevelBreakdown);
        userCreatePanel.add(totalEnergyExpenditureBreakdown);
    }

    public FitnessAppGUI getFitnessAPP() {
        return fitnessAppGUI;
    }

}
