import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BaseFile {
    public static void main(String[] args) {
        // Code that runs in here
        Menu();
    }

    public static void Menu() {
        // Test Data for the calorie tracking
        // So abdullah will be working with variables like these:
        ArrayList<HashMap<String, String>> calorieTrackingData = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> calorieData = new HashMap<>();

        // Test data for the workout tracking
        // So Ali will be working with data like these
        ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();
        HashMap<String, Object> workout1 = new HashMap<>();
        ArrayList<HashMap<String, Object>> exercises = new ArrayList<>();
        HashMap<String, Object> exercise1 = new HashMap<>();
        ArrayList<HashMap<String, Integer>> sets = new ArrayList<>();
        HashMap<String, Integer> set1 = new HashMap<>();

        // So this is how the function is going to work:
        getMenu();
    }

    public static void getMenu() {
        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        // Output the menu
        System.out.println("Please choose from the following options(1-3):");
        System.out.println("1. Calorie Tracking");
        System.out.println("2. Workout Tracking");
        System.out.println("3. View Progress");
        String option = scanner.nextLine();     // Take the user input

        // Check for validity of the input
        while (!(option.equals("1") || option.equals("2") || option.equals("3"))) {
            System.out.println("Enter valid option between 1-3:");
            option = scanner.nextLine();
        }

        switch (option) {
            case "1":
                getCalorieMenu();
                break;
            case "2":
                getWorkoutMenu();
                break;
            case "3":
                getViewMenu();
                break;
        }
    }

    public static void getCalorieMenu() {
        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please answer the following questions:");

        System.out.print("Are you having a snack or a meal?: ");
        String snackOrMeal = scanner.nextLine();

        System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
        String mealType = scanner.nextLine();

        System.out.print("Enter name of food: ");
        String foodName = scanner.nextLine();

        System.out.print("Enter calories of food: ");
        String calories = scanner.nextLine();

        storeCaloriesDataEntry(snackOrMeal, mealType, foodName, calories);
    }

    public static void getWorkoutMenu() {
        // Initialize the scanner for user input
        Scanner scanner = new Scanner(System.in);
        ArrayList<HashMap<String, Object>> exercises = new ArrayList<>();

        // Step 1: Ask the user for the workout type
        System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
        String workoutPlan = scanner.nextLine();

        // Step 2: Ask for the number of exercises
        System.out.print("How many exercises have you done?: ");
        int exercisesCount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left from nextInt()

        for (int i = 0; i < exercisesCount; i++) {
            // Step 3: Input details for each exercise
            System.out.print("Enter name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            System.out.print("Enter number of sets for exercise " + (i + 1) + ": ");
            int setsCount = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            ArrayList<HashMap<String, Integer>> sets = new ArrayList<>();

            for (int j = 0; j < setsCount; j++) {
                // Step 4: Input details for each set
                System.out.print("Enter weight lifted for set " + (j + 1) + ": ");
                int weightLifted = scanner.nextInt();

                System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                int reps = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                // Add this set information to the exercise
                HashMap<String, Integer> setDetails = new HashMap<>();
                setDetails.put("weight", weightLifted);
                setDetails.put("reps", reps);
                sets.add(setDetails);
            }

            // After gathering all sets, add to the exercise
            HashMap<String, Object> exerciseDetails = new HashMap<>();
            exerciseDetails.put("name", exerciseName);
            exerciseDetails.put("sets", sets);
            exercises.add(exerciseDetails);
        }

        // Step 5: Store workout data
        storeWorkoutData(workoutPlan, exercises);
    }

    public static void getViewMenu() {}

    // Abdullah will work on this method
    public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, String calories) {}

    // Ali will work on the following
    // Admittedly, this is slightly more difficult, so I will help out on this part as well
    public static void storeWorkoutData(String workoutPlan, ArrayList<HashMap<String, Object>> exercises) {
        // Create a static list to hold workout plans (replace this with a database or other storage in production)
        ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();

        // Create a new workout entry
        HashMap<String, Object> workout = new HashMap<>();
        workout.put("workoutType", workoutPlan);
        workout.put("exercises", exercises);

        // Add to the global workout list
        workouts.add(workout);

        // Success message
        System.out.println("Workout data saved successfully! Workout Type: " + workoutPlan);
    }

    // Global list of workouts for tracking workout data (must be defined for these methods to work)
    private static final ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getAllExercises() {
        ArrayList<HashMap<String, String>> exercises = new ArrayList<>();

        // Loop through globally stored workouts
        for (HashMap<String, Object> workout : workouts) {
            @SuppressWarnings("unchecked")
            ArrayList<HashMap<String, Object>> workoutExercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");

            if (workoutExercises != null) {
                for (HashMap<String, Object> exercise : workoutExercises) {
                    HashMap<String, String> exerciseSummary = new HashMap<>();
                    // Extract the exercise name and add it to the list
                    exerciseSummary.put("name", (String) exercise.get("name"));
                    exercises.add(exerciseSummary);
                }
            }
        }

        return exercises;
    }

    public static void addToExercise(String exerciseName, HashMap<String, String> newSetData) {
        // Check if given workouts data is valid
        boolean exerciseFound = false;

        for (HashMap<String, Object> workout : workouts) {
            // Access the list of exercises
            @SuppressWarnings("unchecked")
            ArrayList<HashMap<String, Object>> exercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");

            if (exercises != null) {
                for (HashMap<String, Object> existingExercise : exercises) {
                    if (existingExercise.get("name").equals(exerciseName)) {
                        // Access the sets for the exercise
                        @SuppressWarnings("unchecked")
                        ArrayList<HashMap<String, Integer>> sets = (ArrayList<HashMap<String, Integer>>) existingExercise.get("sets");

                        if (sets != null) {
                            // Create a new set
                            HashMap<String, Integer> newSet = new HashMap<>();
                            newSet.put("weight", Integer.valueOf(newSetData.get("weight")));
                            newSet.put("reps", Integer.valueOf(newSetData.get("reps")));

                            // Add the set to the exercise
                            sets.add(newSet);

                            exerciseFound = true;
                            System.out.println("Successfully added a new set to exercise: " + exerciseName);
                            break;
                        }
                    }
                }
            }

            if (exerciseFound) break;
        }

        if (!exerciseFound) {
            System.out.println("Error: Exercise '" + exerciseName + "' not found.");
        }
    }

    public static HashMap<String, String> makeNewSet(String weightLifted, String reps) {
        // Create a new set as a HashMap
        HashMap<String, String> newSet = new HashMap<>();
        newSet.put("weight", weightLifted);
        newSet.put("reps", reps);

        System.out.println("New set created successfully: " + weightLifted + "kg x " + reps + " reps");
        return newSet;
    }}