package MainFiles;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Data.Workout;
import Data.Exercise;
import Enums.WorkoutPlan;
import java.util.ArrayList;
import Data.Calories;
import Data.User;
import Enums.MealTime;
import Enums.MealType;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public class ProjectController {
    private static final int MAX_CALORIES = 20000;  // Stores the max calories that can be stored for a single item
    private static final int MAX_EXERCISE_NUMBER = 10;  // Stores the maximum number of exercises that can be stored for a single workout
    private static final int MAX_SET_NUMBER = 5;    // Stores the maximum number of sets that can be stored for a single exercise
    private static User user = new User("", "", "");    // This is the user of the menu

    @FXML
    private VBox mainView;

    @FXML
    public void showCalorieView() {
        user.addCalorieData(new Calories(MealType.MEAL, MealTime.LUNCH, "Biryani", 1200));
        user.addCalorieData(new Calories(MealType.MEAL, MealTime.LUNCH, "Biryani", 1200));
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
        mainView.getChildren().add(textArea);
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

        // Validate inputs using your existing validation methods
        if (Workout.validateWeightLifted(kgInput) && Workout.validateReps(repsInput)) {
            double kilograms = Double.parseDouble(kgInput);
            int reps = Integer.parseInt(repsInput);

            Exercise exercise = new Exercise(name, kilograms, reps);
            currentWorkout.getExercises().add(exercise);
            updateSummary();

            workoutName.clear();
            workoutKilograms.clear();
            workoutReps.clear();
        } else {
            exerciseSummary.appendText("Invalid input, please enter valid values.\n");
        }
    }

    private void updateSummary() {
        StringBuilder summary = new StringBuilder();
        for (Exercise e : currentWorkout.getExercises()) {
            summary.append(e.getExerciseName())
                    .append(" - ")
                    .append(e.getWeightLifted())
                    .append("kg x ")
                    .append(e.getReps())
                    .append(" reps\n");
        }
        exerciseSummary.setText(summary.toString());
    }

    @FXML
    private void resetWorkoutView() {
        workoutDetails.setVisible(false);
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
}

