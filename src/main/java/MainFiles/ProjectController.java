package MainFiles;

import Data.*;
import com.sun.tools.javac.Main;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
    private Label errorStatus;
    @FXML
    private Label successStatus;
    @FXML
    private VBox navigationBar;
    @FXML
    private Button toggleSidebarButton;
    @FXML
    private VBox viewMenu;
    private boolean isSidebarCollapsed = false;

    // These are miscellaneous and general functions used throughout the controller

    @FXML
    private void collapseSidebar() {
        Timeline collapse = new Timeline(
                new KeyFrame(Duration.millis(300),
                        new KeyValue(navigationBar.prefWidthProperty(), 0),
                        new KeyValue(navigationBar.minWidthProperty(), 0)
                )
        );

        // Slight visual nudge for content
        TranslateTransition contentShift = new TranslateTransition(Duration.millis(300), mainView);
        contentShift.setFromX(0);
        contentShift.setToX(-10);
        contentShift.setAutoReverse(true);
        contentShift.setCycleCount(2);

        collapse.setOnFinished(e -> {
            toggleSidebarButton.setVisible(true);
            toggleSidebarButton.setOpacity(0);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), toggleSidebarButton);
            fadeIn.setToValue(1);
            fadeIn.play();
            isSidebarCollapsed = true;
        });

        collapse.play();
        contentShift.play();
        toggleSidebarButton.setDisable(false);
        toggleSidebarButton.setMouseTransparent(false);
        toggleSidebarButton.toFront();
    }

    public void expandSidebar() {
        Timeline expand = new Timeline(
                new KeyFrame(Duration.millis(300),
                        new KeyValue(navigationBar.prefWidthProperty(), 290),
                        new KeyValue(navigationBar.minWidthProperty(), 290),
                        new KeyValue(navigationBar.maxWidthProperty(), 290)
                )
        );

        TranslateTransition contentShift = new TranslateTransition(Duration.millis(300), mainView);
        contentShift.setFromX(0);
        contentShift.setToX(10);
        contentShift.setAutoReverse(true);
        contentShift.setCycleCount(2);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), toggleSidebarButton);
        fadeOut.setToValue(0);
        fadeOut.play();
        toggleSidebarButton.setVisible(false);

        expand.setOnFinished(e -> {
            isSidebarCollapsed = false;
        });

        expand.play();
        contentShift.play();
    }

    @FXML
    private void showStatus(boolean isSuccess, String message) {
        if (isSuccess) {
            successStatus.setText(message);
            // Fade in
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), successStatus);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // Pause
            PauseTransition pause = new PauseTransition(Duration.seconds(2));

            // Fade out
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), successStatus);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            fadeIn.setOnFinished(e -> pause.play());
            pause.setOnFinished(e -> fadeOut.play());

            fadeIn.play();
        } else {
            errorStatus.setText(message);
            // Fade in
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), errorStatus);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // Pause
            PauseTransition pause = new PauseTransition(Duration.seconds(2));

            // Fade out
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), errorStatus);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            fadeIn.setOnFinished(e -> pause.play());
            pause.setOnFinished(e -> fadeOut.play());

            fadeIn.play();
        }
    }

    @FXML
    private void resetView() {
        for (Node child : mainView.getChildren()) {
            child.setDisable(true);
            child.setVisible(false);
        }
    }

    // The following are all the view menu functions

    @FXML
    public void showCalorieView() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("🍎 TODAY'S CALORIES TRACKING DATA 🍎");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();
        String outputText = "";

        // Check if its empty
        if (calorieTrackingData.isEmpty()) {
            outputText += "No calorie data available for today.";
        } else {
            // Go through all the array objects and print them out in correct format
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
        viewMenu.getChildren().add(textArea);
    }

    @FXML
    public void showWorkoutView() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("💪 TODAY'S WORKOUT DATA 💪");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Workout> workouts = user.getWorkoutData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (workouts.isEmpty()) {
            outputText.append("No workout data available for today.");
        } else {
            // Go through all the array objects and print them out in correct format
            workouts.forEach(workout -> {
                outputText.append("🏋️ Workout Plan: ✨ ")
                        .append(workout.getWorkoutPlan())
                        .append(" ✨\n");
                ArrayList<Exercise> exercises = workout.getExercises();
                exercises.forEach(exercise -> {
                    outputText.append("Exercise: ")
                            .append(exercise.getExerciseName())
                            .append("\n");
                    ArrayList<Set> sets = exercise.getSets();
                    sets.forEach(set -> {
                        outputText.append(" - ")
                                .append(set.getWeightLifted())
                                .append("kg x ")
                                .append(set.getReps())
                                .append(" reps\n");
                    });
                });
            });
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    @FXML
    public void showMealBreakdown() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("🍽️ MEAL BREAKDOWN 🍽️");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Calories> calories = user.getCalorieData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (calories.isEmpty()) {
            outputText.append("No calories data available for today.");
        } else {
            ArrayList<Calories> snacks = new ArrayList<>();
            ArrayList<Calories> breakfast = new ArrayList<>();
            ArrayList<Calories> lunch = new ArrayList<>();
            ArrayList<Calories> dinner = new ArrayList<>();

            for (Calories data : calories) {
                if (data.getSnackOrMeal() == MealType.SNACK) {
                    snacks.add(data);
                } else {
                    switch (data.getMealTime()) {
                        case BREAKFAST -> breakfast.add(data);
                        case LUNCH -> lunch.add(data);
                        case DINNER -> dinner.add(data);
                    }
                }
            }
            outputText.append("🍳 Breakfast:\n");
            int totalBreakfast = 0;
            for (Calories data : breakfast) {
                outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                totalBreakfast += data.getCalories();
            }
            outputText.append("  Total: " + totalBreakfast + " kcal\n");

            outputText.append("\n🥪 Lunch:\n");
            int totalLunch = 0;
            for (Calories data : lunch) {
                outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                totalLunch += data.getCalories();
            }
            outputText.append("  Total: " + totalLunch + " kcal\n");

            outputText.append("\n🍝 Dinner:\n");
            int totalDinner = 0;
            for (Calories data : dinner) {
                outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                totalDinner += data.getCalories();
            }
            outputText.append("  Total: " + totalDinner + " kcal\n");

            outputText.append("\n🍇 Snacks:\n");
            int totalSnacks = 0;
            for (Calories data : snacks) {
                outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                totalSnacks += data.getCalories();
            }
            outputText.append("  Total: " + totalSnacks + " kcal\n");
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    @FXML private VBox showParticularMealView;
    @FXML private ComboBox<Object> particularMealComboBox;
    @FXML private TextArea particularTextArea;

    @FXML
    public void showParticularMealCalories() {
        resetView();
        collapseSidebar();
        showParticularMealView.setDisable(false);
        showParticularMealView.setVisible(true);
        particularMealComboBox.getItems().clear();
        for (MealTime time : MealTime.values()) {
            if (time != MealTime.NULL) {
                particularMealComboBox.getItems().add(time);
            }
        }
        particularMealComboBox.getItems().add(MealType.SNACK);
        // Retrieve the calorie data
        ArrayList<Calories> calories = user.getCalorieData();

        particularMealComboBox.setOnAction(event -> {
            particularTextArea.setText("");
            StringBuilder outputText = new StringBuilder();
            Object userChoice = particularMealComboBox.getValue();
            MealType mealType = null;
            MealTime mealTime = null;
            if (userChoice == MealType.SNACK) {
                mealType = (MealType) particularMealComboBox.getValue();
            } else {
                mealTime = (MealTime) particularMealComboBox.getValue();
            }

            // Depending on the search value, print out the right data
            if (mealTime == null) {
                outputText.append("==== " + mealType + " ====\n");
            } else {
                outputText.append("==== " + mealTime + " ====\n");
            }
            int totalCalories = 0;
            for (Calories data : calories) {
                if (mealType == MealType.SNACK) {
                    if (data.getSnackOrMeal() == MealType.SNACK) {
                        totalCalories += data.getCalories();
                        outputText.append("1. " + data.getFoodName() + "\n");
                        outputText.append("Calories:" + data.getCalories() + " kcal\n");
                    }
                } else {
                    if (data.getMealTime() == mealTime) {
                        totalCalories += data.getCalories();
                        outputText.append("1. " + data.getFoodName() + "\n");
                        outputText.append("Calories:" + data.getCalories() + " kcal\n");
                    }
                }
            }
            outputText.append("Total calories: " + totalCalories + " kcal\n");
            particularTextArea.setText(outputText.toString());
        });
    }

    @FXML
    public void showAverageCalories() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("🍽️ AVERAGE CALORIES PER MEAL TYPE 🍽️");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Calories> calories = user.getCalorieData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (calories.isEmpty()) {
            outputText.append("No calories data available for today.");
        } else {
            // Go through all the array objects and print them out in correct format
            // Initialize all the variables needed, with the average variables storing the total as well
            float avgBreakfastCalories = 0f;
            int breakfastCount = 0;
            float avgLunchCalories = 0f;
            int lunchCount = 0;
            float avgDinnerCalories = 0f;
            int dinnerCount = 0;
            float avgSnackCalories = 0f;
            int snacksCount = 0;

            // Add to both count and total of the corresponding meal type
            for (Calories data : calories) {
                MealTime mealTime = data.getMealTime();
                MealType snackOrMeal = data.getSnackOrMeal();
                if (snackOrMeal == MealType.SNACK) {
                    avgSnackCalories += data.getCalories();
                    snacksCount++;
                } else {
                    switch (mealTime) {
                        case BREAKFAST:
                            avgBreakfastCalories += data.getCalories();
                            breakfastCount++;
                            break;
                        case LUNCH:
                            avgLunchCalories += data.getCalories();
                            lunchCount++;
                            break;
                        case DINNER:
                            avgDinnerCalories += data.getCalories();
                            dinnerCount++;
                            break;
                    }
                }
            }
            // Calculate the averages
            if (avgBreakfastCalories != 0) {
                avgBreakfastCalories /= breakfastCount;
            }
            if (avgLunchCalories != 0) {
                avgLunchCalories /= lunchCount;
            }
            if (avgDinnerCalories != 0) {
                avgDinnerCalories /= dinnerCount;
            }
            if (avgSnackCalories != 0) {
                avgSnackCalories /= snacksCount;
            }

            // Output them
            outputText.append("Average Calories in Breakfast: " + avgBreakfastCalories + " kcal\n");
            outputText.append("Average Calories in Lunch: " + avgLunchCalories + " kcal\n");
            outputText.append("Average Calories in Dinner: " + avgDinnerCalories + " kcal\n");
            outputText.append("Average Calories in Snacks: " + avgSnackCalories + " kcal\n");
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    @FXML
    public void showSnacksVsCalories() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("🍽️ TOTAL CALORIES: MEALS & SNACKS 🍽️");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Calories> calories = user.getCalorieData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (calories.isEmpty()) {
            outputText.append("No calories data available for today.");
        } else {
            // Go through all the array objects and print them out in correct format
            // Find the total calories of both snacks and meals
            int snackCalories = 0;
            int mealCalories = 0;
            for (Calories data : calories) {
                MealType snackOrMeal = data.getSnackOrMeal();
                if (snackOrMeal.equals(MealType.SNACK)) {
                    snackCalories += data.getCalories();
                } else {
                    mealCalories += data.getCalories();
                }
            }

            // Calculate the percentage of each consumed
            float percentageSnack = ((float) snackCalories / (mealCalories + snackCalories)) * 100;
            float percentageMeal = ((float) mealCalories / (mealCalories + snackCalories)) * 100;

            // Output the calculated data
            outputText.append("🍟Total Snack Calories: " + snackCalories + " kcal\n");
            outputText.append("🍝 Total Meal Calories: " + mealCalories + " kcal\n");
            outputText.append(String.format( "Percentage Of Calories From Snacks: %.2f%%\n", percentageSnack));
            outputText.append(String.format("Percentage Of Calories From Meals: %.2f%%\n", percentageMeal));
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    @FXML
    public void showWorkoutVolume() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("💪 WORKOUT VOLUME DATA 💪");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Workout> workouts = user.getWorkoutData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (workouts.isEmpty()) {
            outputText.append("No workout data available for today.");
        } else {
            // Go through all the array objects and print them out in correct format
            // Go through each exercise, do the calculation and then print it out
            for (Workout data : workouts) {
                for (Exercise exercise : data.getExercises()) {
                    outputText.append("Name of exercise: " + exercise.getExerciseName() + "\n");
                    int setCount = 0;
                    float volume = 1;
                    for (Set set : exercise.getSets()) {
                        float weight = set.getWeightLifted();
                        volume *= set.getReps() * weight;
                        setCount++;
                    }
                    volume *= setCount;
                    outputText.append("Volume: " + Math.round(volume) + " kg\n");
                }
            }
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    @FXML
    public void showHeaviestLiftPerExercise() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("💪 HEAVIEST LIFT PER EXERCISE 💪");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Workout> workouts = user.getWorkoutData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (workouts.isEmpty()) {
            outputText.append("No workout data available for today.");
        } else {
            // Go through each exercise, do the calculation and then print it out
            // Go through each exercise, and print out the max weight lifted for that exercise
            for (Workout data : workouts) {
                for (Exercise exercise : data.getExercises()) {
                    outputText.append("Name: " + exercise.getExerciseName() + "\n");
                    float maxWeightLift = 0f;
                    for (Set set : exercise.getSets()) {
                        float currentWeightLift = set.getWeightLifted();
                        if (maxWeightLift < currentWeightLift) {
                            maxWeightLift = currentWeightLift;
                        }
                    }
                    outputText.append("Max Weight Lifted : " + maxWeightLift + "kg\n");
                }
            }
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    @FXML private VBox caloriesConsumedVsGoalView;
    @FXML private TextField caloriesGoalInput;
    @FXML private TextArea goalView;
    @FXML private Button goalButton;

    @FXML
    public void showCaloriesConsumedVsGoal() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        caloriesConsumedVsGoalView.setDisable(false);
        caloriesConsumedVsGoalView.setVisible(true);

        goalButton.setOnAction(event -> {
            try {
                int caloriesInput = Integer.parseInt(caloriesGoalInput.getText());
                if (caloriesInput > 0) {
                    StringBuilder outputText = new StringBuilder();
                    outputText.append("===================================\n");
                    outputText.append("    CALORIES VS GOAL COMPARISON    \n");
                    outputText.append("===================================\n");
                    // Retrieve the calorie data
                    ArrayList<Calories> calorieTrackingData = user.getCalorieData();

                    // If empty, return to View MainFiles.Menu
                    if (calorieTrackingData.isEmpty()) {
                        outputText.append("No calories data found.\n");
                        return;
                    }

                    // Find the total calories from the data
                    int totalCalories = 0;
                    for (Calories data : calorieTrackingData) {
                        totalCalories += data.getCalories();
                    }

                    int calorieGoal = caloriesInput;
                    outputText.append("Calories Consumed: " + totalCalories + " kcal\n");
                    outputText.append("Calorie Goal: " + calorieGoal + " kcal\n");

                    // Compare the total calories to the goal entered by the user
                    if (totalCalories > calorieGoal) {
                        outputText.append("You exceeded your goal by " + (totalCalories - calorieGoal) + " kcal.\n");
                    } else {
                        outputText.append("You are under your goal by " + (calorieGoal - totalCalories) + " kcal.\n");
                    }
                    goalView.setText(outputText.toString());
                } else {
                    showStatus(false, "Invalid calories input. Must be a positive number.");
                }
            } catch (NumberFormatException e) {
                showStatus(false, "Invalid calories input. Must be a number.");
            }
        });
    }

    @FXML
    public void showPerformanceSummary() {
        viewMenu.getChildren().clear();
        resetView();
        collapseSidebar();
        viewMenu.setDisable(false);
        viewMenu.setVisible(true);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("view-textarea");
        Label viewTitle = new Label();
        viewTitle.setText("📊 PERFORMANCE SUMMARY 📊");
        viewTitle.setUnderline(true);
        viewTitle.getStyleClass().add("view-title");
        viewMenu.getChildren().add(viewTitle);
        // Retrieve the calorie data
        ArrayList<Workout> workouts = user.getWorkoutData();
        ArrayList<Calories> calories = user.getCalorieData();
        StringBuilder outputText = new StringBuilder();

        // Check if its empty
        if (workouts.isEmpty() && calories.isEmpty()) {
            outputText.append("No data available for today.");
        } else {
            // Go through all the array objects and print them out in correct format
            outputText.append("🍽️      MEAL BREAKDOWN       🍽️");
            outputText.append("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            if (!calories.isEmpty()) {
                ArrayList<Calories> snacks = new ArrayList<>();
                ArrayList<Calories> breakfast = new ArrayList<>();
                ArrayList<Calories> lunch = new ArrayList<>();
                ArrayList<Calories> dinner = new ArrayList<>();

                for (Calories data : calories) {
                    if (data.getSnackOrMeal() == MealType.SNACK) {
                        snacks.add(data);
                    } else {
                        switch (data.getMealTime()) {
                            case BREAKFAST -> breakfast.add(data);
                            case LUNCH -> lunch.add(data);
                            case DINNER -> dinner.add(data);
                        }
                    }
                }
                outputText.append("🍳 Breakfast:\n");
                int totalBreakfast = 0;
                for (Calories data : breakfast) {
                    outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                    totalBreakfast += data.getCalories();
                }
                outputText.append("  Total: " + totalBreakfast + " kcal\n");

                outputText.append("\n🥪 Lunch:");
                int totalLunch = 0;
                for (Calories data : lunch) {
                    outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                    totalLunch += data.getCalories();
                }
                outputText.append("  Total: " + totalLunch + " kcal\n");

                outputText.append("\n🍝 Dinner:");
                int totalDinner = 0;
                for (Calories data : dinner) {
                    outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                    totalDinner += data.getCalories();
                }
                outputText.append("  Total: " + totalDinner + " kcal\n");

                outputText.append("\n🍇 Snacks:");
                int totalSnacks = 0;
                for (Calories data : snacks) {
                    outputText.append("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal\n");
                    totalSnacks += data.getCalories();
                }
                outputText.append("  Total: " + totalSnacks + " kcal\n");
            } else {
                outputText.append("No calories data available for today.");
            }

            outputText.append("\n💪      WORKOUT VOLUME DATA       💪");
            outputText.append("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            if(!workouts.isEmpty()) {
                // Go through all the array objects and print them out in correct format
                // Go through each exercise, do the calculation and then print it out
                for (Workout data : workouts) {
                    for (Exercise exercise : data.getExercises()) {
                        outputText.append("Name of exercise: " + exercise.getExerciseName() + "\n");
                        int setCount = 0;
                        float volume = 1;
                        for (Set set : exercise.getSets()) {
                            float weight = set.getWeightLifted();
                            volume *= set.getReps() * weight;
                            setCount++;
                        }
                        volume *= setCount;
                        outputText.append("Volume: " + Math.round(volume) + " kg\n");
                    }
                }
            } else {
                outputText.append("No workout data available for today.");
            }
        }
        textArea.setText(outputText.toString());
        viewMenu.getChildren().add(textArea);
    }

    // The following are all the workout functions

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
    private void saveNewWorkout() {
        user.addWorkoutData(currentWorkout);
        showStatus(true, "Successfully saved new workout!");
        currentWorkout = null;
        exerciseList.clear();
        resetWorkoutView();
    }

    @FXML
    private VBox WorkoutInputView;

    @FXML
    private void ShowAddNewWorkout(){
        resetView();
        resetWorkoutView();
        collapseSidebar();
        WorkoutInputView.setVisible(true);
        WorkoutInputView.setDisable(false);
    }

    // The following are all the calorie input functions

    @FXML
    private ComboBox<MealType> mealTypeCombo;

    @FXML
    private ComboBox<MealTime> mealTimeCombo;

    @FXML
    private TextField calorieNumber;

    @FXML
    private TextField foodNameText;

    @FXML
    private Button saveCalories;

    @FXML
    public void calorieInput() {
        resetView();
        collapseSidebar();
        calorieInput.setVisible(true);
        calorieInput.setDisable(false);

        // Combo Boxes
        mealTimeCombo.getItems().clear();
        mealTypeCombo.getItems().clear();

        mealTypeCombo.setPromptText("Select Type");
        mealTypeCombo.getItems().addAll(MealType.values());

        mealTimeCombo.setPromptText("Select Meal Time");
        mealTimeCombo.getItems().addAll(MealTime.values());

        // MealType Listener for Snack

        mealTypeCombo.setOnAction(e -> {
            MealType selectedType = mealTypeCombo.getValue();
            if (selectedType == MealType.SNACK) {
                mealTimeCombo.setDisable(true);
                mealTimeCombo.setValue(null);
            } else {
                mealTimeCombo.setDisable(false);
            }
        });

        // Text Fields
        foodNameText.setPromptText("Enter Food Name");
        calorieNumber.setPromptText("Enter Calories");

        // Submit Button
        saveCalories.setOnAction(e -> {
            try {
                MealType type = mealTypeCombo.getValue();
                MealTime time = mealTimeCombo.getValue();
                String food = foodNameText.getText().trim();
                int cals = Integer.parseInt(calorieNumber.getText().trim());

                if (type == null || food.isEmpty() || cals < 0 || (type != MealType.SNACK && time == null)) {
                showStatus(false, "Invalid input, please enter valid values.");
                }

                Calories newEntry = new Calories(type, time, food, cals);
                user.addCalorieData(newEntry);

                foodNameText.clear();
                calorieNumber.clear();
                mealTypeCombo.setValue(null);
                mealTimeCombo.setValue(null);

                //showMessage("Calorie entry added successfully!");
                showStatus(true, "Successfully saved new calories!");

            } catch (Exception ex) {
                //showMessage("⚠️ Invalid input. Please check your fields.");
                showStatus(false,"Invalid input. Please check your fields.");
            }
        });
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

    // TODO: Finish this
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
        resetView();
        menuBar.setDisable(false);
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
        errorStatus.setOpacity(0);
        successStatus.setOpacity(0);
    }

}

