import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        // Code that runs in here
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
        System.out.println("4. Exit");
        String option = scanner.nextLine();     // Take the user input

        // Check for validity of the input
        while (!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4"))) {
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
            case "4":
                System.out.println("Exited Program");
                break;
        }
    }

    public static void getCalorieMenu() {
        // Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please answer the following questions:");

        // Asking the user if it's a meal or a snack
        System.out.print("Are you having a snack or a meal?: ");
        String snackOrMeal = scanner.nextLine();

        // Checking if the user inputted the correct values
        while(!snackOrMeal.toLowerCase().equals("snack") && !snackOrMeal.toLowerCase().equals("meal")) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("Are you having a snack or a meal?: ");
            snackOrMeal = scanner.nextLine();
        }

        // If it's a meal, we have to asking the user which type of meal did they have.
        String mealType = "";
        if (snackOrMeal.toLowerCase().equals("meal")) {
            // Asking the user if the meal was eaten on breakfast, lunch, or lunch
            System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
            mealType = scanner.nextLine();

            // Checking if the user inputted the correct values
            while(!mealType.toLowerCase().equals("breakfast") && !mealType.toLowerCase().equals("lunch") && !mealType.toLowerCase().equals("dinner")) {
                System.out.println("Invalid input. Please try again.");
                System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
                mealType = scanner.nextLine();
            }
        }

        // Asking the user what is the food's meal
        System.out.print("Enter name of food: ");
        String foodName = scanner.nextLine();

        // Checking if the user inputted the correct values
        while(!foodName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Food has to contain alphanumeric characters and space.");
            System.out.print("Enter name of food: ");
            foodName = scanner.nextLine();
        }

        // Asking for calorie amount and validating calorie input
        String caloriesInput = "";
        boolean isCaloriesInputValid = false;
        int calories = 0;
        while (isCaloriesInputValid == false) {
            System.out.print("Enter number of calories: ");
            caloriesInput = scanner.nextLine();
            try {
                // if it's a number, check if it's within the range limit
                calories = Integer.parseInt(caloriesInput);
                // if it's not within the range, invalid input
                if (calories < 0 || calories > 20000) {
                    System.out.println("Invalid input. Has to be in range 0-20,000. Please try again.");
                }
                else {
                    // if valid, then print
                    isCaloriesInputValid = true;
                }
            // if imput isn't a number -> invalid input
            } catch(NumberFormatException e) {
                System.out.println("Invalid input. Has to be a number. Please try again.");
            }
        }

        Calories.storeCaloriesDataEntry(snackOrMeal, mealType, foodName, calories);
        getMenu();
    }

    public static void getWorkoutMenu() {
        // Initialize the scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the workout type
        System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
        String workoutPlan = scanner.nextLine();

        while (!workoutPlan.equals("Push") && !workoutPlan.equals("Pull") && !workoutPlan.equals("Leg") && !workoutPlan.equals("Upper") && !workoutPlan.equals("Lower")) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
            workoutPlan = scanner.nextLine();
        }

        // Ask for the number of exercises with validation
        boolean isExerciseValid  = false;
        int exercisesCount = 0;     // Initialize exerciseCount to 0
        while (!isExerciseValid) {
            System.out.print("How many exercises have you done?: ");
            String exercisesCountInput = scanner.nextLine();
            try {
                exercisesCount = Integer.parseInt(exercisesCountInput);
                // Check to see if the user entered more than the max number of exercises to be stored
                if (exercisesCount > 10) {
                    System.out.println("Invalid input. Cannot store more than 10 exercises. Please try again.");
                } else {
                    isExerciseValid = true;
                }
            } catch (NumberFormatException e) {     // Check to see if the user did not enter a number
                System.out.println("Invalid input. Can only be a number. Please try again.");
            }
        }

        // This will later be stored in the workouts variable
        ArrayList<HashMap<String, Object>> exercises = new ArrayList<>();

        for (int i = 0; i < exercisesCount; i++) {
            // Input details for each exercise
            System.out.print("Enter name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            // Will be stored in exercise, must be initialised each new exercise
            ArrayList<HashMap<String, Integer>> sets = new ArrayList<>();

            // Input number of sets with validation
            boolean isSetValid  = false;
            int setsCount = 0;     // Initialize setsCount to 0
            while (!isSetValid) {
                System.out.print("Enter number of sets for exercise " + (i + 1) + ": ");
                String setsCountInput = scanner.nextLine();
                try {
                    setsCount = Integer.parseInt(setsCountInput);
                    // Check to see if the user entered more than the max number of sets to be stored
                    if (setsCount > 5) {
                        System.out.println("Invalid input. Cannot store more than 5 sets. Please try again.");
                    } else {
                        isSetValid = true;
                    }
                } catch (NumberFormatException e) {     // Check to see if the user did not enter a number
                    System.out.println("Invalid input. Can only be a number. Please try again.");
                }
            }
            for (int j = 0; j < setsCount; j++) {
                // Input details for each set
                boolean isWeightValid = false;
                int weightLifted = 0;
                while (!isWeightValid) {
                    System.out.print("Enter weight lifted for set " + (j + 1) + " (in kg): ");
                    String weightLiftedInput = scanner.nextLine();
                    try {
                        weightLifted = Integer.parseInt(weightLiftedInput);
                        isWeightValid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Can only be a number. Please try again.");
                    }
                }

                boolean isRepsValid = false;
                int reps = 0;
                while (!isRepsValid) {
                    System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                    String repsInput = scanner.nextLine();
                    try {
                        reps = Integer.parseInt(repsInput);
                        isRepsValid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Can only be a number. Please try again.");
                    }
                }

                // Create new set - coded this way for easier transition into OOP
                HashMap<String, Integer> newSet = Workout.createSet(weightLifted, reps);
                sets.add(newSet);
            }

            // Create new exercise - coded this way for easier transition to OOP
            HashMap<String, Object> newExercise = Workout.createExercise(exerciseName, sets);
            exercises.add(newExercise);
        }

        // Create new workout - coded this way for easier transition to OOP
        HashMap<String, Object> newWorkout = Workout.createWorkout(workoutPlan, exercises);
        Workout.storeWorkoutData(newWorkout);
        getMenu();
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

        if (Calories.calorieTrackingData.isEmpty()) {
            System.out.println("No calorie tracking data available.");
        } else {
            for (HashMap<String, Object> entry : Calories.calorieTrackingData) {
                System.out.println("Snack/Meal: " + entry.get("snackOrMeal"));
                System.out.println("Meal Type: " + entry.get("mealType"));
                System.out.println("Food Name: " + entry.get("name"));
                System.out.println("Calories: " + entry.get("calories"));
                System.out.println("-------------------------");
            }
        }
        getViewMenu();
    }

    // Method to display workout tracking data
    public static void viewWorkoutData() {
        System.out.println("=== Workout Tracking Data ===");

        if (Workout.workouts.isEmpty()) {
            System.out.println("No workout tracking data available.");
        } else {
            for (HashMap<String, Object> workout : Workout.workouts) {
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
}
