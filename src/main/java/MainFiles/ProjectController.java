package MainFiles;

import Data.Calories;
import Data.User;
import Enums.MealTime;
import Enums.MealType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    public void calorieInput() {
        // Input Fields
        ComboBox<MealType> mealTypeCombo = new ComboBox<>();
        mealTypeCombo.getItems().addAll(MealType.values());
        mealTypeCombo.setPromptText("Select Type");

        ComboBox<MealTime> mealTimeCombo = new ComboBox<>();
        mealTimeCombo.getItems().addAll(MealTime.values());
        mealTimeCombo.setPromptText("Select Meal Time");

        TextField foodNameField = new TextField();
        foodNameField.setPromptText("Enter Food Name");

        TextField calorieField = new TextField();
        calorieField.setPromptText("Enter Calories");

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
    }

    // Helper message popup
    private void showMessage(String msg) {
        TextArea messageArea = new TextArea(msg);
        messageArea.setEditable(false);
        messageArea.setWrapText(true);
        messageArea.setPrefHeight(50);
        mainView.getChildren().add(messageArea);
    }

}
