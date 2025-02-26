import java.util.ArrayList;
import java.util.HashMap;

public class Workout {
    // This is the public workouts data structure to be used to store the data
    public static ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();

    /**
     * @param weightLifted -> An int that stores the weight to be stored into the workouts array
     * @param reps -> An int that stores the number of reps to be stored into the workouts array
     * @return set -> Hashmap<String, Integer> storing the values of weight lifted and number of reps
     * */
    public static HashMap<String, Integer> createSet(int weightLifted, int reps) {
        HashMap<String, Integer> set = new HashMap<>();     // Initialize a new Hashmap
        set.put("weightLifted", weightLifted);      // Store the weightLifted
        set.put("reps", reps);      // Store the reps
        return set;     // Return the set Hashmap
    }

    /**
     * @param exerciseName -> A String that stores the name of the exercise to be stored
     * @param sets -> An ArrayList of Hashmaps that stores all the sets in the exercise
     * @return exercise -> Hashmap<String, Object> that stores the name and sets of the exercise
     * */
    public static HashMap<String, Object> createExercise(String exerciseName, ArrayList<HashMap<String, Integer>> sets) {
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
}
