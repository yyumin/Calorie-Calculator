package ui;

import model.FoodIntake;
import model.FoodList;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FitnessAPP extends JFrame implements ActionListener {
    private static final String JSON_STORE_USER = "./data/user.json";
    private static final String JSON_STORE = "./data/foodList.json";

    private final JLabel saveLabel1 = new JLabel();
    private final JLabel saveLabel2 = new JLabel();
    private final JLabel loadLabel1 = new JLabel();
    private final JLabel loadLabel2 = new JLabel();

    private JsonWriter jsonWriterUserInfo;
    private JsonReader jsonReaderUserInfo;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private FoodList foodList1;
    private User user1;

    // EFFECTS: run application
    public FitnessAPP() throws FileNotFoundException {
        super("Fitness APP");

        this.setSize(500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jsonWriterUserInfo = new JsonWriter(JSON_STORE_USER);
        jsonReaderUserInfo = new JsonReader(JSON_STORE_USER);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        foodList1 = new FoodList(); //instantiate it first
        constructMainPanel();
    }

    //EFFECTS: constructs main panel with buttons, and adds the buttons to the main
    //         action listener
    @SuppressWarnings("methodlength")
    private void constructMainPanel() {
        JPanel mainPanel = new JPanel();

        JLabel label1 = new JLabel();
        label1.setText(logTime());

        JButton createButton = new JButton("Create Personal Info");
        createButton.setActionCommand("createButton");
        createButton.addActionListener(this);

        JButton checkButton = new JButton("Check Personal Info");
        checkButton.setActionCommand("checkButton");
        checkButton.addActionListener(this);

        JButton addButton = new JButton("Add Food");
        addButton.setActionCommand("addButton");
        addButton.addActionListener(this);

        JButton deleteButton = new JButton("Delete Food");
        deleteButton.setActionCommand("deleteButton");
        deleteButton.addActionListener(this);

        JButton displayButton = new JButton("Display Food");
        displayButton.setActionCommand("displayButton");
        displayButton.addActionListener(this);

        JButton saveButton = new JButton("Save Data");
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);

        JButton loadButton = new JButton("Load Data");
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);

        ImageIcon image = new ImageIcon("./data/image.jpg");
        Image image2 = image.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        //provide the correct path to the image file if it's in a different location
        ImageIcon scaledIcon = new ImageIcon(image2);
        JLabel label = new JLabel();
        label.setText("Nutrition is the key.");
        label.setIcon(scaledIcon);
        label.setBackground(Color.white);
        label.setOpaque(true);//display background color
        label.setVerticalAlignment(JLabel.BOTTOM);

        mainPanel.add(createButton);
        mainPanel.add(checkButton);
        mainPanel.add(addButton);
        mainPanel.add(deleteButton);
        mainPanel.add(displayButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.add(label);

        add(mainPanel,BorderLayout.NORTH); //take up the north space of our JFrame
        add(label1,BorderLayout.PAGE_END);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));//set the component alignments and gaps b/w
        mainPanel.setPreferredSize(new Dimension(500,300));//set size of the JPanel
        setVisible(true);
        pack();
    }

    //EFFECTS: creates current date
    private String logTime() {
        LocalDateTime date = LocalDateTime.now();
        String month = date.getMonth().name();
        String dayOfWeek = date.getDayOfWeek().name();
        int dayNumber = date.getDayOfMonth();
        String fullDate = dayOfWeek + " " + month + " " + dayNumber;
        return fullDate;
    }

    //EFFECTS:
    private void deleteFood() {
        FoodRemoveFrame foodRemoveFrame = new FoodRemoveFrame(this);
        foodRemoveFrame.removeFood();
    }

    public void printFood() {
        FoodDisplayFrame foodDisplayFrame = new FoodDisplayFrame(this);
        foodDisplayFrame.displayFoodList();
    }

    public void printClientInfo() {
        UserInfoFrame userInfoFrame = new UserInfoFrame(this);
        userInfoFrame.displayClientInfo();
    }


    private void saveUserInfo() {
        this.add(saveLabel1);
        try {
            jsonWriterUserInfo.open();
            jsonWriterUserInfo.writeUser(user1);
            jsonWriterUserInfo.close();
            saveLabel1.setText("Saved your info " + " to " + JSON_STORE_USER);
        } catch (FileNotFoundException e) {
            JLabel exceptionLabel = new JLabel("Unable to write to file: " + JSON_STORE_USER);
            this.add(exceptionLabel);
        }
    }

    // EFFECTS: saves the foodList to file
    private void saveFoodInfo() {
        this.add(saveLabel2);
        try {
            jsonWriter.open();
            jsonWriter.writeFoodList(foodList1);
            jsonWriter.close();
            saveLabel2.setText("Saved your info " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JLabel exceptionLabel = new JLabel("Unable to write to file: " + JSON_STORE);
            this.add(exceptionLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads information from file
    private void loadUserInfo() {
        this.add(loadLabel1);
        try {
            user1 = jsonReaderUserInfo.readUser();
            loadLabel1.setText("Loaded your info from " + JSON_STORE_USER);
        } catch (IOException e) {
            JLabel exceptionLabel = new JLabel("Unable to read from file: " + JSON_STORE_USER);
            this.add(exceptionLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads information from file
    private void loadFoodInfo() {
        this.add(loadLabel2);
        try {
            foodList1 = jsonReader.readFoodList();
            loadLabel1.setText("Loaded your daily consumed calories from " + JSON_STORE);
        } catch (IOException e) {
            JLabel exceptionLabel = new JLabel("Unable to read from file: " + JSON_STORE);
            this.add(exceptionLabel);
        }
    }

    //MODIFIES: this
    //EFFECTS: constructs User Information Creation frame and panel, prompts user to input basic information
    private void runCreate() {
        UserCreateFrame userCreateFrame = new UserCreateFrame(this);
        userCreateFrame.runUserInfoFrame();
    }

    //MODIFIES: this
    //EFFECTS: constructs Food Creation frame and panel, prompts user to input food info
    private void addFood() {
        FoodCreateFrame foodCreateFrame = new FoodCreateFrame(this);
        foodCreateFrame.runFoodCreatFrame();
    }

    public FoodList getFoodList() {
        return foodList1;
    }

    public void setUser(User user) {
        this.user1 = user;
    }

    public User getUser() {
        return user1;
    }

    // EFFECTS: processes button clicks and runs appropriate methods
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "createButton":
                runCreate();
                break;
            case "checkButton":
                printClientInfo();
                break;
            case "addButton":
                addFood();
                break;
            case "deleteButton":
                deleteFood();
                break;
            case "displayButton":
                printFood();
                break;
            case "saveButton":
                saveUserInfo();
                saveFoodInfo();
                break;
            case "loadButton":
                loadUserInfo();
                loadFoodInfo();
                break;
        }
    }
}
