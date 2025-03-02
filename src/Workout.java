import java.util.ArrayList;
import java.util.HashMap;

public class Workout {
    // This is the public workouts data structure to be used to store the data
    private static ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();

    /**
     * This function checks if the workout plan is one from the options
     * Also prints out a meaningful invalid input message
     * @param workoutPlan -> A String that stores the user input of workout plan
     * @return -> A boolean that represents whether the workout plan is valid
     * */
    public static boolean validateWorkoutPlan(String workoutPlan) {
        if (workoutPlan.equalsIgnoreCase("Push") || workoutPlan.equalsIgnoreCase("Pull") || workoutPlan.equalsIgnoreCase("Leg") || workoutPlan.equalsIgnoreCase("Upper") || workoutPlan.equalsIgnoreCase("Lower")) {
            return true;
        }
        System.out.println("Invalid workout plan. Try again.");
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
            if (weightLifted >= 0.1 && weightLifted <= 2442.2) {
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
            if (reps > 0 && reps <= 100) {
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

    /**
     * @param weightLifted -> An int that stores the weight to be stored into the workouts array
     * @param reps -> An int that stores the number of reps to be stored into the workouts array
     * @return set -> Hashmap<String, Integer> storing the values of weight lifted and number of reps
     * */
    public static HashMap<String, Object> createSet(float weightLifted, int reps) {
        HashMap<String, Object> set = new HashMap<>();     // Initialize a new Hashmap
        set.put("weightLifted", weightLifted);      // Store the weightLifted
        set.put("reps", reps);      // Store the reps
        return set;     // Return the set Hashmap
    }

    /**
     * @param exerciseName -> A String that stores the name of the exercise to be stored
     * @param sets -> An ArrayList of Hashmaps that stores all the sets in the exercise
     * @return exercise -> Hashmap<String, Object> that stores the name and sets of the exercise
     * */
    public static HashMap<String, Object> createExercise(String exerciseName, ArrayList<HashMap<String, Object>> sets) {
        HashMap<String, Object> exercise = new HashMap<>();     // Initialize a new Hashmap
        exercise.put("exerciseName", exerciseName);     // Store the exercise name
        exercise.put("sets", sets);     // Store the collection of sets
        return exercise;        // Return the exercise Hashmap
    }

    /**
     * @param workoutPlan -> A String that stores the name of the workout plan
     * @param exercises -> ArrayList of Hashmaps that store the exercises in this workout
     * @return workout -> Hashmap<String, Integer> storing the values of weight lifted and number of reps
     * */
    public static HashMap<String, Object> createWorkout(String workoutPlan, ArrayList<HashMap<String, Object>> exercises) {
        HashMap<String, Object> workout = new HashMap<>();      // Initialize a new Hashmap
        workout.put("workoutPlan", workoutPlan);        // Store the workout plan
        workout.put("exercises", exercises);        // Store the collection of exercises
        return workout;     // Return the workout Hashmap
    }

    /**
     * Adds a workout onto the workouts ArrayList
     * @param workout -> A Hashmap which stores all the data for a workout
     * */
    public static void storeWorkoutData(HashMap<String, Object> workout) {
        workouts.add(workout);      // Add the workout to the workouts ArrayList
    }

    public static ArrayList<HashMap<String, Object>> getWorkouts() {
        return workouts;
    }
}
