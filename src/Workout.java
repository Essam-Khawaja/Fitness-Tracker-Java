import java.util.ArrayList;
import java.util.HashMap;

public class Workout {
    public static ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();

    public static HashMap<String, Integer> createSet(int weightLifted, int reps) {
        HashMap<String, Integer> set = new HashMap<>();
        set.put("weightLifted", weightLifted);
        set.put("reps", reps);
        return set;
    }

    public static HashMap<String, Object> createExercise(String exerciseName, ArrayList<HashMap<String, Integer>> sets) {
        HashMap<String, Object> exercise = new HashMap<>();
        exercise.put("exerciseName", exerciseName);
        exercise.put("sets", sets);
        return exercise;
    }

    public static HashMap<String, Object> createWorkout(String workoutPlan, ArrayList<HashMap<String, Object>> exercises) {
        HashMap<String, Object> workout = new HashMap<>();
        workout.put("workoutPlan", workoutPlan);
        workout.put("exercises", exercises);
        return workout;
    }

    public static void storeWorkoutData(HashMap<String, Object> workout) {
        workouts.add(workout);
    }
}
