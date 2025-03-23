package Data;

import Enums.MealTime;
import Enums.WorkoutPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This is a helper class to store all the workout data
 * @author Syed Essam Uddin Khawaja
 * @author Ali Gad
 */
public class Workout{
    private static final ArrayList<HashMap<String, Object>> workoutData = new ArrayList<>();
    // This is the public workouts data structure to be used to store the data
    private WorkoutPlan workoutPlan;
    private ArrayList<Exercise> exercises;

    // Getters and Setters
    public Workout(WorkoutPlan workoutPlan, ArrayList<Exercise> exercises) {
        this.workoutPlan = workoutPlan;
        this.exercises = exercises;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        boolean equals = false;
        if (this.workoutPlan.equals(workout.getWorkoutPlan()) && this.exercises.equals(workout.getExercises())) {
            equals = true;
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutPlan, exercises);
    }

    //TO-String
    public String toString() {
        String objectString = "";
        objectString += workoutPlan + ",";
        int count = 0;
        for (Exercise exercise: exercises) {
            objectString += exercise.toString();
            count++;
            if (count != exercises.size()) {
                objectString += ",";
            }
        }
        return objectString;
    }

    private static final double MAX_WEIGHT_LIFTED = 2442.2;     // Stores the maximum weight that can be stored for a set
    private static final double MIN_WEIGHT_LIFTED = 0.1;    // Stores the minimum weight that can be stored for a set
    private static final int MAX_REPS = 100;        // Stores the maximum number of reps that can be stored for a single set

    /**
     * This function checks if the workout plan is one from the options
     * Also prints out a meaningful invalid input message
     * @param workoutPlan -> A String that stores the user input of workout plan
     * @return -> A boolean that represents whether the workout plan is valid
     * */
    public static boolean validateWorkoutPlan(String workoutPlan) {
        if (workoutPlan.equalsIgnoreCase("1") || workoutPlan.equalsIgnoreCase("2") || workoutPlan.equalsIgnoreCase("3") || workoutPlan.equalsIgnoreCase("4") || workoutPlan.equalsIgnoreCase("5")|| workoutPlan.equalsIgnoreCase("6")) {
            return true;
        }
        System.out.println("Invalid input. Try again.");
        return false;
    }

    /**
     * This function performs the following validity checks:
     *      1. Checks if the weight lifted is a float, since it does not need more than 3 decimal places for it to be stored
     *      2. Check if the weight lifted is less than 2442.2kg, since that is the world record
     *      3. Check if the weight lifted is greater than 0; i.e. it's a positive number
     * Prints out a meaningful invalid input message
     * @param weightLiftedInput -> A String that stores the user input of the weight lifted
     * @return -> A boolean that represents whether the weight lifted is valid
     * */
    public static boolean validateWeightLifted(String weightLiftedInput) {
        try {
            float weightLifted = Float.parseFloat(weightLiftedInput);
            if (weightLifted >= MIN_WEIGHT_LIFTED && weightLifted <= MAX_WEIGHT_LIFTED) {
                return true;
            } else {
                System.out.println("Invalid weight. Must be in the range 0.1kg - 2442.2 kg. Try again.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight. Must be a number/decimal. Try again.");
            return false;
        }
    }

    /**
     * This function performs the following validity checks:
     *      1. Check if the number of reps is an integer
     *      2. Check if it is a positive number
     *      3. Check if it is below 100, since no one is doing more than 100 reps on a single exercise
     * Prints out a meaningful error message
     * @param repsInput -> A String that stores the user input of the number of reps
     * @return -> A boolean that represents whether the number of reps is a valid input
     * */
    public static boolean validateReps(String repsInput) {
        try {
            int reps = Integer.parseInt(repsInput);
            if (reps > 0 && reps <= MAX_REPS) {
                return true;
            } else {
                System.out.println("Invalid number of reps. Must be in the range 1-100. Try again.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of reps. Must be a whole number. Try again.");
            return false;
        }
    }
}
