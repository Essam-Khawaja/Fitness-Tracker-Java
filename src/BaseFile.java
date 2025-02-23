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
        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please answer the following questions:");

        System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
        String workoutPlan = scanner.nextLine();

        System.out.print("How many exercises have you done?: ");
        int exercisesCount = scanner.nextInt();
        for (int i = 0; i < exercisesCount; i++) {
            System.out.print("Enter name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            System.out.print("Enter number of sets for exercise " + (i + 1) + ": ");
            int setsCount = scanner.nextInt();
            for (int j = 0; j < setsCount; j++) {
                System.out.print("Enter weight lifted for set " + (j + 1) + ": ");
                String weightLifted = scanner.nextLine();

                System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                String reps = scanner.nextLine();
                HashMap<String, String> thisSet =  makeNewSet(weightLifted, reps);
                addToExercise(exerciseName, thisSet);
            }
        }
        storeWorkoutData(workoutPlan, getAllExercises());
    }

    public static void getViewMenu() {
        System.out.println("=== View Progress ===");
        System.out.println("Please select what you'd like to view:");
        System.out.println("1. Calorie Tracking Data");
        System.out.println("2. Workout Tracking Data");
        System.out.println("3. Back to Main Menu");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!(option.equals("1") || option.equals("2") || option.equals("3"))) {
            System.out.println("Invalid option. Please select 1, 2, or 3:");
            option = scanner.nextLine();
        }

        switch (option) {
            case "1":
                viewCalorieData();
                break;
            case "2":
                viewWorkoutData();
                break;
            case "3":
                getMenu();
                break;
        }
    }

    // Method to display calorie tracking data
    public static void viewCalorieData() {
        System.out.println("=== Calorie Tracking Data ===");

        // Assuming calorieTrackingData contains all the data
        // Replace with actual reference to the data source
        ArrayList<HashMap<String, String>> calorieTrackingData = new ArrayList<>(); // Test example
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calorie tracking data available.");
        } else {
            for (HashMap<String, String> entry : calorieTrackingData) {
                System.out.println("Snack/Meal: " + entry.get("snackOrMeal"));
                System.out.println("Meal Type: " + entry.get("mealType"));
                System.out.println("Food Name: " + entry.get("foodName"));
                System.out.println("Calories: " + entry.get("calories"));
                System.out.println("-------------------------");
            }
        }
        getViewMenu();
    }

    // Method to display workout tracking data
    public static void viewWorkoutData() {
        System.out.println("=== Workout Tracking Data ===");

        // Assuming workouts contains all the workout data
        // Replace with actual reference to the data source
        ArrayList<HashMap<String, Object>> workouts = new ArrayList<>(); // Example data structure
        if (workouts.isEmpty()) {
            System.out.println("No workout tracking data available.");
        } else {
            for (HashMap<String, Object> workout : workouts) {
                System.out.println("Workout Plan: " + workout.get("workoutPlan"));
                ArrayList<HashMap<String, Object>> exercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");
                for (HashMap<String, Object> exercise : exercises) {
                    System.out.println("- Exercise: " + exercise.get("exerciseName"));
                    ArrayList<HashMap<String, Integer>> sets = (ArrayList<HashMap<String, Integer>>) exercise.get("sets");
                    for (HashMap<String, Integer> set : sets) {
                        System.out.println("  - Set: " + set);
                    }
                }
                System.out.println("-------------------------");
            }
        }
        getViewMenu();
    }

    // Abdullah will work on this method
    public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, String calories) {}

    // Ali will work on the following
    // Admittedly, this is slightly more difficult, so I will help out on this part as well
    public static void storeWorkoutData(String workoutPlan, ArrayList<HashMap<String, String>> exercises) {}

    public static ArrayList<HashMap<String, String>> getAllExercises() {
        ArrayList<HashMap<String, String>> exercises = new ArrayList<>();
        return exercises;
    }

    public static void addToExercise(String exerciseName, HashMap<String, String> exercise) {}

    public static HashMap<String, String> makeNewSet(String weightLifted, String reps) {
        HashMap<String, String> thisSet = new HashMap<>();
        return thisSet;
    }
}
