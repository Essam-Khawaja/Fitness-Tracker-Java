import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BaseFile {
    public static ArrayList<HashMap<String, String>> calorieTrackingData = new ArrayList<>();
    public static ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();

    public static void main(String[] args) {
        // Code that runs in here
        Menu();
    }

    public static void Menu() {
        // Test Data for the calorie tracking
        // So abdullah will be working with variables like these:
        HashMap<String, String> calorieData = new HashMap<>();
        calorieData.put("calories", "100");
        calorieData.put("type", "snack");
        calorieTrackingData.add(calorieData);
        HashMap<String, String> calorieData2 = new HashMap<>();
        calorieData2.put("calories", "100");
        calorieData2.put("type", "snack");
        calorieTrackingData.add(calorieData2);

        // Test data for the workout tracking
        // So Ali will be working with data like these
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

        boolean quit = false;

        while (!quit) {
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
            System.out.println("Would you like to continue? (y/n): ");
            String quitAnswer = scanner.nextLine();

            if (quitAnswer.equalsIgnoreCase("n")) {
                quit = true;
            }
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

        // Ask the user for the workout type
        System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
        String workoutPlan = scanner.nextLine();

        // Ask for the number of exercises
        System.out.print("How many exercises have you done?: ");
        int exercisesCount = Integer.parseInt(scanner.nextLine());

        // This will later be stored in the workouts variable
        ArrayList<HashMap<String, Object>> exercises = new ArrayList<>();

        for (int i = 0; i < exercisesCount; i++) {
            // Input details for each exercise
            System.out.print("Enter name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            // Will be stored in exercise, must be initialised each new exercise
            ArrayList<HashMap<String, String>> sets = new ArrayList<>();

            System.out.print("Enter number of sets for exercise " + (i + 1) + ": ");
            int setsCount = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < setsCount; j++) {
                // Input details for each set
                System.out.print("Enter weight lifted for set " + (j + 1) + ": ");
                String weightLifted = scanner.nextLine();

                System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                String reps = scanner.nextLine();

                // Create new set - coded this way for easier transition into OOP
                HashMap<String, String> newSet = createSet(weightLifted, reps);
                sets.add(newSet);
            }

            // Create new exercise - coded this way for easier transition to OOP
            HashMap<String, Object> newExercise = createExercise(exerciseName, sets);
            exercises.add(newExercise);
        }

        // Create new workout - coded this way for easier transition to OOP
        HashMap<String, Object> newWorkout = createWorkout(workoutPlan, exercises);
        storeWorkoutData(newWorkout);
    }

    public static HashMap<String, String> createSet(String weightLifted, String reps) {
        HashMap<String, String> set = new HashMap<>();
        set.put("weightLifted", weightLifted);
        set.put("reps", reps);
        return set;
    }

    public static HashMap<String, Object> createExercise(String exerciseName, ArrayList<HashMap<String, String>> sets) {
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

    // Abdullah will work on this method
    public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, String calories) {
    }

    public static void getViewMenu() {
        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the data you wish to view: ");

        System.out.println("1. Today's Calories");
        System.out.println("2. Today's Workout");
        System.out.println("3. Meal Breakdown");
        System.out.println("4. Calories Of A Particular Meal");
        System.out.println("5. Average Calories Per Meal");
        System.out.println("6. Calories Consumption Snacks vs Foods");
        System.out.println("7. Total Volume of Workout");
        System.out.println("8. Heaviest Lift Per Exercise");
        System.out.println("9. Calories Consumed vs. Estimated Calories Burned");
        System.out.println("10. Performance Summary");

        String option = scanner.nextLine();
        switch (option) {
            case "1":
                viewCalorieData();
                break;
            case "2":
                viewWorkoutData();
                break;
            case "3":
                viewMealBreakdown();
                break;
            case "4":
                viewMealData();
                break;
            case "5":
                viewAverageCaloriesPerMeal();
                break;
            case "6":
                viewFoodsVsSnacks();
                break;
            case "7":
                viewTotalWorkoutVolume();
                break;
            case "8":
                viewHeaviestExercises();
                break;
            case "9":
                viewCaloriesWorkoutComparison();
                break;
            case "10":
                viewPerformanceSummary();
                break;
        }
    }

    public static void viewCalorieData() {
        System.out.println("Calories" + "Type");
        for (HashMap<String, String> foodItem : calorieTrackingData) {
            System.out.println(foodItem.get("calories") + foodItem.get("type"));
        }
    }

    public static void viewWorkoutData() {
        for (HashMap<String, Object> workout : workouts) {
            System.out.println(workout);
        }
    }

    public static void viewMealBreakdown() {
    }

    public static void viewMealData() {
    }

    public static void viewAverageCaloriesPerMeal() {
    }

    public static void viewFoodsVsSnacks() {
    }

    public static void viewTotalWorkoutVolume() {
    }

    public static void viewHeaviestExercises() {
    }

    public static void viewCaloriesWorkoutComparison() {
    }

    public static void viewPerformanceSummary() {
    }
}