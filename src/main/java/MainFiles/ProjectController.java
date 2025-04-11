package MainFiles;

import Data.Calories;
import Data.User;
import Enums.MealTime;
import Enums.MealType;
import Save.Save;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class ProjectController {
    private static final int MAX_CALORIES = 20000;  // Stores the max calories that can be stored for a single item
    private static final int MAX_EXERCISE_NUMBER = 10;  // Stores the maximum number of exercises that can be stored for a single workout
    private static final int MAX_SET_NUMBER = 5;    // Stores the maximum number of sets that can be stored for a single exercise
    private static User user = new User("", "", "");    // This is the user of the menu

    @FXML
    private VBox mainView;
    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane newWorkoutDataPane;
    @FXML
    private AnchorPane LoginPage;
    @FXML
    private AnchorPane loginDataPane;
    @FXML
    private AnchorPane MainMenu;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField emailInput;
    @FXML
    private Label loginStatusLabel;

    @FXML
    public void showCalorieView() {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefWidth(600);
        textArea.setPrefHeight(600);
        // Retrieve the calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();
        String outputText = "";

        // Check if its empty
        if (calorieTrackingData.isEmpty()) {
            outputText += "No calorie data available for today.";
        } else {
            // Go through all the array objects and print them out in correct format
            outputText += "\n===================================\n";
            outputText += "   TODAY'S CALORIES TRACKING DATA  \n";
            outputText += "===================================\n";
            int totalCalories = 0;  // Stores the total of all the calories stored
            for (Calories data : calorieTrackingData) {
                outputText += "\n. " + data.getFoodName();
                outputText += "\n    Type:" + data.getSnackOrMeal();
                if (!(data.getSnackOrMeal() == MealType.SNACK)) {
                    outputText += "\n    Time:" + data.getMealTime();
                }
                outputText += "\n    Calories:" + data.getCalories() + " kcal";
                totalCalories += data.getCalories();
            }
            outputText += "\n🔥 Today's Total Calories: " + totalCalories + " kcal 🔥\n";
        }
        textArea.setText(outputText);
    }

    @FXML
    public void Load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load File");
        File file = fileChooser.showSaveDialog(mainView.getScene().getWindow());
        if (file != null) {
            Save.LoadData(user, file.getPath());
        }
    }

    @FXML
    public void Save(ActionEvent event) {
        Save.SaveData(user);
    }

    @FXML
    public void SaveSeparateFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(mainView.getScene().getWindow());
        if (file != null) {
            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            Save.SaveSeparateFile(file, user);
        }
    }

    @FXML
    public void Exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void logout(ActionEvent event) {
        user = new User("", "", "");
        MainMenu.setVisible(false);
        MainMenu.setDisable(true);
        LoginPage.setVisible(true);
        LoginPage.setDisable(false);
    }

    /**
     *
     * @param actionEvent
     * @author Syed Essam Uddin Khawaja
     */
    @FXML
    public void logIn(ActionEvent actionEvent) {
        boolean loggedIn = false;
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        if (username.equals("")) {
            loginStatusLabel.setText("Username is required.");
            return;
        } else if (password.equals("")) {
            loginStatusLabel.setText("Password is required.");
            return;
        } else if (email.equals("")) {
            loginStatusLabel.setText("Email is required.");
            return;
        }

        int validateUser = Save.ValidateUser(user);

        if (validateUser == 1) {
            loggedIn = true;
        } else if (validateUser == 0) {
            passwordInput.requestFocus();
            loginStatusLabel.setText("Password was wrong. Please try again.");
        } else {
            passwordInput.requestFocus();
            usernameInput.requestFocus();
            emailInput.requestFocus();
            loginStatusLabel.setText("User with this email does not exist.");
        }

        if (loggedIn) {
            LoginPage.setVisible(false);
            LoginPage.setDisable(true);
            loginDataPane.setVisible(true);
            loginDataPane.setDisable(false);
        }
    }

    /**
     *
     * @param actionEvent
     * @author Syed Essam Uddin Khawaja
     */
    @FXML
    public void signUp(ActionEvent actionEvent) {
        boolean signedUp = false;

        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();

        if (username.equals("")) {
            loginStatusLabel.setText("Username is required.");
            return;
        } else if (password.equals("")) {
            loginStatusLabel.setText("Password is required.");
            return;
        } else if (email.equals("")) {
            loginStatusLabel.setText("Email is required.");
            return;
        }

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        int validateUser = Save.ValidateUser(user);
        if (validateUser == -1) {
            Save.SaveNewUser(user);
            signedUp = true;
        } else {
            loginStatusLabel.setText("User with this email already exists.");
            emailInput.requestFocus();
        }

        if (signedUp) {
            LoginPage.setVisible(false);
            LoginPage.setDisable(true);
            MainMenu.setVisible(true);
            MainMenu.setDisable(false);
            menuBar.setDisable(false);
        }
    }

    /**
     *
     * @param actionEvent
     * @author Syed Essam Uddin Khawaja
     */
    @FXML
    public void loadPreviousData(ActionEvent actionEvent) {
        Save.LoadData(user, "src/main/java/Save/Save.csv");
        loginDataPane.setVisible(false);
        loginDataPane.setDisable(true);
        MainMenu.setVisible(true);
        MainMenu.setDisable(false);
        menuBar.setDisable(false);
    }

    /**
     * Fix this thing when you add status bar
     * @param actionEvent
     */
    @FXML
    public void loadNewData(ActionEvent actionEvent) {
        boolean foundUser = Save.LoadData(user, "src/main/java/Save/Save.csv");
        if (!foundUser) {}
    }

    /**
     *
     * @param actionEvent
     * @author Syed Essam Uddin Khawaja
     */
    @FXML
    public void moveToMainMenu(ActionEvent actionEvent) {
        loginDataPane.setVisible(false);
        loginDataPane.setDisable(true);
        MainMenu.setVisible(true);
        MainMenu.setDisable(false);
        menuBar.setDisable(false);
    }

    /**
     * @author Syed Essam Uddin Khawaja
     */
    @FXML
    public void showWorkoutInputPane() {
        newWorkoutDataPane.setVisible(true);
    }

    /**
     * @author Syed Essam Uddin Khawaja
     */
    @FXML
    public void initialize() {
        menuBar.setDisable(true);
    }

}
