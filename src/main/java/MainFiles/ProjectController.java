package MainFiles;

import Data.*;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Enums.WorkoutPlan;

import java.lang.reflect.Field;
import java.util.ArrayList;

import Enums.MealTime;
import Enums.MealType;
import Save.Save;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;

import java.io.File;
import java.util.ArrayList;

public class ProjectController {
    private static final int MAX_CALORIES = 20000;  // Stores the max calories that can be stored for a single item
    private static final int MAX_EXERCISE_NUMBER = 10;  // Stores the maximum number of exercises that can be stored for a single workout
    private static final int MAX_SET_NUMBER = 5;    // Stores the maximum number of sets that can be stored for a single exercise
    private static User user = new User("", "", "");    // This is the user of the menu

    @FXML
    private AnchorPane mainView;
    @FXML
    private VBox calorieInput;
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
    private HBox workoutButtons;
    @FXML
    private VBox workoutDetails;
    @FXML
    private Label workoutLabel;
    @FXML
    private TextField workoutName, workoutKilograms, workoutReps;
    @FXML
    private TextArea exerciseSummary;
    @FXML
    private TextField workoutSets;

    private Workout currentWorkout;
    private ArrayList<Exercise> exerciseList = new ArrayList<>();

    @FXML
    private void selectWorkout(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String workoutName = clickedButton.getText().trim().toUpperCase().replace(" ", "_");

        // Special handling for inconsistent naming
        if (workoutName.equals("LEG")) {
            workoutName = "LEGS";
        }

        WorkoutPlan selectedWorkoutPlan = WorkoutPlan.getPlanEnum(workoutName);

        if (selectedWorkoutPlan != null) {
            currentWorkout = new Workout(selectedWorkoutPlan, new ArrayList<>());
            workoutLabel.setText(clickedButton.getText());
            workoutDetails.setDisable(false);
            workoutDetails.setVisible(true);
            exerciseList.clear();
            exerciseSummary.clear();
        } else {
            System.out.println("Workout plan not recognized: " + workoutName);
            workoutDetails.setVisible(false);
        }
    }

    @FXML
    private void addExercise() {
        String name = workoutName.getText();
        String kgInput = workoutKilograms.getText();
        String repsInput = workoutReps.getText();
        String setsInput = workoutSets.getText();

        // Validate inputs using your existing validation methods
        if (Workout.validateWeightLifted(kgInput) && Workout.validateReps(repsInput)) {
            float kilograms = Float.parseFloat(kgInput);
            int reps = Integer.parseInt(repsInput);
            int sets = Integer.parseInt(setsInput);

            ArrayList<Set> setList = new ArrayList<>();
            for (int i = 0; i < sets; i++) {
                Set thisSet = new Set(reps, kilograms);
                setList.add(thisSet);
            }

            Exercise exercise = new Exercise(name, setList);
            currentWorkout.getExercises().add(exercise);
            updateSummary();

            workoutName.clear();
            workoutKilograms.clear();
            workoutReps.clear();
            workoutSets.clear();
        } else {
            exerciseSummary.appendText("Invalid input, please enter valid values.\n");
        }
    }

    private void updateSummary() {
        StringBuilder summary = new StringBuilder();
        for (Exercise e : currentWorkout.getExercises()) {
            summary.append(e.getExerciseName() + ": ");
            for (Set s: e.getSets()) {
                summary.append("\n - ").append(s.getWeightLifted())
                        .append("kg x ")
                        .append(s.getReps())
                        .append(" reps");
            }
            summary.append("\n");
        }
        exerciseSummary.setText(summary.toString());
    }

    @FXML
    private void resetWorkoutView() {
        workoutDetails.setVisible(false);
        workoutDetails.setDisable(true);
        workoutButtons.setVisible(true);
        workoutName.clear();
        workoutKilograms.clear();
        workoutReps.clear();
        exerciseSummary.clear();
        currentWorkout = null;
    }

    @FXML
    private VBox WorkoutInputView;

    @FXML
    private void ShowAddNewWorkout(){
        WorkoutInputView.setVisible(true);
        WorkoutInputView.setDisable(false);
    }

    @FXML
    public void calorieInput() {
        calorieInput.setVisible(true);
        calorieInput.setDisable(false);
        calorieInput.getChildren().clear();
        // Input Fields
        ComboBox<MealType> mealTypeCombo = new ComboBox<>();
        mealTypeCombo.getItems().addAll(MealType.values());
        mealTypeCombo.setPromptText("Select Type");
        calorieInput.getChildren().add(mealTypeCombo);

        ComboBox<MealTime> mealTimeCombo = new ComboBox<>();
        mealTimeCombo.getItems().addAll(MealTime.values());
        mealTimeCombo.setPromptText("Select Meal Time");
        calorieInput.getChildren().add(mealTimeCombo);

        TextField foodNameField = new TextField();
        foodNameField.setPromptText("Enter Food Name");
        calorieInput.getChildren().add(foodNameField);

        TextField calorieField = new TextField();
        calorieField.setPromptText("Enter Calories");
        calorieInput.getChildren().add(calorieField);

        // Submit Button
        javafx.scene.control.Button submitButton = new javafx.scene.control.Button("Add Calorie Entry");
        submitButton.setOnAction(e -> {
            try {
                MealType type = mealTypeCombo.getValue();
                MealTime time = mealTimeCombo.getValue();
                String food = foodNameField.getText().trim();
                int cals = Integer.parseInt(calorieField.getText().trim());

                if (type == null || food.isEmpty() || cals < 0 || (type != MealType.SNACK && time == null)) {
                    throw new IllegalArgumentException();
                }

                Calories newEntry = new Calories(type, time, food, cals);
                user.addCalorieData(newEntry);

                foodNameField.clear();
                calorieField.clear();
                mealTypeCombo.setValue(null);
                mealTimeCombo.setValue(null);
                showMessage("Calorie entry added successfully!");

            } catch (Exception ex) {
                showMessage("⚠️ Invalid input. Please check your fields.");
            }
        });
        calorieInput.getChildren().add(submitButton);
    }

    // Helper message popup
    private void showMessage(String msg) {
        TextArea messageArea = new TextArea(msg);
        messageArea.setEditable(false);
        messageArea.setWrapText(true);
        messageArea.setPrefHeight(50);
        mainView.getChildren().add(messageArea);
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
        MainMenu.setDisable(true);
        MainMenu.setVisible(false);
        loginDataPane.setVisible(false);
        loginDataPane.setDisable(true);
        LoginPage.setVisible(true);
        LoginPage.setDisable(false);
        menuBar.setDisable(true);
    }

}

