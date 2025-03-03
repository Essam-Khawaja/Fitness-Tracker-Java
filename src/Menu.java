import java.util.*;

/**
 * This is the main class from which the program is run
 * It also holds the menu functionality
 * Due -> March 1st 2025
 * Tutorial -> T01
 * @author Syed Essam Uddin Khawaja
 * @author Ali Gad
 * @author Abdullah Al-Dhaibani
 */
public class Menu {
    private static final int MAX_CALORIES = 20000;  // Stores the max calories that can be stored for a single item
    private static final int MAX_EXERCISE_NUMBER = 10;  // Stores the maximum number of exercises that can be stored for a single workout
    private static final int MAX_SET_NUMBER = 5;    // Stores the maximum number of sets that can be stored for a single exercise

    public static void main(String[] args) {
        getMenu();
    }

    /**
     * Displays the main menu and handles user input for
     * navigating between options such as calorie tracking,
     * workout tracking, viewing progress, or exiting the program.
     */
    public static void getMenu() {
        // Displaying the main menu
        System.out.println("=== Main Menu ===");
        System.out.println("Please choose from the following options (1-4):");
        System.out.println("1. Calorie Tracking");
        System.out.println("2. Workout Tracking");
        System.out.println("3. View Progress");
        System.out.println("4. Exit");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        // Validate the user's input
        while (!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4"))) {
            System.out.println("Invalid option, please enter a number between 1 and 4:");
            option = scanner.nextLine();
        }

        // Handle the options
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
                System.out.println("Exiting the program...");
                break;
        }
    }

    /**
     * Displays the calorie menu, collects details about a snack or meal,
     * validates the input, logs the data, and stores it in the calorie tracking list.
     * Prompts the user to specify the type of meal, name, and calorie count.
     */
    public static void getCalorieMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please answer the following questions:");

        // Ask if it's a meal or a snack
        System.out.print("Are you having a snack or a meal?: ");
        String snackOrMeal = scanner.nextLine();

        // Validate input
        while (!snackOrMeal.equalsIgnoreCase("snack") && !snackOrMeal.equalsIgnoreCase("meal")) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("Are you having a snack or a meal?: ");
            snackOrMeal = scanner.nextLine();
        }

        // If it's a meal, ask for the specific type
        String mealType = "";
        if (snackOrMeal.equalsIgnoreCase("meal")) {
            System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
            mealType = scanner.nextLine();

            while (!mealType.equalsIgnoreCase("breakfast") && !mealType.equalsIgnoreCase("lunch")
                    && !mealType.equalsIgnoreCase("dinner")) {
                System.out.println("Invalid input. Please try again.");
                System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
                mealType = scanner.nextLine();
            }
        }

        // Ask for the food's name
        System.out.print("Enter the name of the food: ");
        String foodName = scanner.nextLine();

        // Ask for calorie amount with validation
        String caloriesInput;
        boolean isCaloriesInputValid = false;
        int calories = 0;

        while (!isCaloriesInputValid) {
            System.out.print("Enter the number of calories: ");
            caloriesInput = scanner.nextLine();
            try {
                calories = Integer.parseInt(caloriesInput);
                if (calories < 0 || calories > MAX_CALORIES)
                    System.out.println("Invalid input. Number must be in the range 0-20,000. Please try again.");
                else {
                    isCaloriesInputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid whole number.");
            }
        }

        // Log the calorie data
        Calories.storeCaloriesDataEntry(snackOrMeal, mealType, foodName, calories);

        // Check if the user wants to continue, with validation
        System.out.println("Calorie entry added successfully!");
        System.out.println("Would you like to continue? (Y/N): ");
        String returnMenuOption = scanner.nextLine();
        while (!returnMenuOption.equalsIgnoreCase("y") && !returnMenuOption.equalsIgnoreCase("n")) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Would you like to continue? (Y/N): ");
            returnMenuOption = scanner.nextLine();
        }
        if (returnMenuOption.equalsIgnoreCase("Y")) {
            getCalorieMenu();
        } else if (returnMenuOption.equalsIgnoreCase("N")) {
            getMenu();
        }
    }

    /**
     * Displays the workout menu, collects details about the workout plan,
     * validates the input, gathers exercises, sets and repetitions, and
     * stores them in the workout tracking list.
     */
    public static void getWorkoutMenu() {
        // Initialize the scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the workout type
        System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
        String workoutPlan = scanner.nextLine();

        boolean isWorkoutValid = Workout.validateWorkoutPlan(workoutPlan);

        while (!isWorkoutValid) {
            System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
            workoutPlan = scanner.nextLine();
            isWorkoutValid = Workout.validateWorkoutPlan(workoutPlan);
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
                if (exercisesCount > MAX_EXERCISE_NUMBER) {
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

        // Loop through exercise count times
        for (int i = 0; i < exercisesCount; i++) {
            // Input details for each exercise
            System.out.print("Enter name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            // Will be stored in exercise, must be initialised each new exercise
            ArrayList<HashMap<String, Object>> sets = new ArrayList<>();

            // Input number of sets with validation
            boolean isSetValid  = false;
            int setsCount = 0;     // Initialize setsCount to 0
            while (!isSetValid) {
                System.out.print("Enter number of sets for exercise " + (i + 1) + ": ");
                String setsCountInput = scanner.nextLine();
                try {
                    setsCount = Integer.parseInt(setsCountInput);
                    // Check to see if the user entered more than the max number of sets to be stored
                    if (setsCount > MAX_SET_NUMBER) {
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
                System.out.print("Enter weight lifted for set " + (j + 1) + " (in kg): ");
                String weightLiftedInput = scanner.nextLine();
                boolean isWeightValid = Workout.validateWeightLifted(weightLiftedInput);

                while (!isWeightValid) {
                    System.out.println("Invalid input. Must be a number. Please try again.");
                    System.out.print("Enter weight lifted for set " + (j + 1) + " (in kg): ");
                    weightLiftedInput = scanner.nextLine();
                    isWeightValid = Workout.validateWeightLifted(weightLiftedInput);
                }

                float weightLifted = Float.parseFloat(weightLiftedInput);

                System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                String repsInput = scanner.nextLine();
                boolean isRepsValid = Workout.validateReps(repsInput);

                while (!isRepsValid) {
                    System.out.println("Invalid input. Must be a number. Please try again.");
                    System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                    repsInput = scanner.nextLine();
                    isRepsValid = Workout.validateReps(repsInput);
                }

                int reps = Integer.parseInt(repsInput);

                // Create new set - coded this way for easier transition into OOP
                HashMap<String, Object> newSet = Workout.createSet(weightLifted, reps);
                sets.add(newSet);
            }

            // Create new exercise - coded this way for easier transition to OOP
            HashMap<String, Object> newExercise = Workout.createExercise(exerciseName, sets);
            exercises.add(newExercise);
        }

        // Create new workout - coded this way for easier transition to OOP
        HashMap<String, Object> newWorkout = Workout.createWorkout(workoutPlan, exercises);
        Workout.storeWorkoutData(newWorkout);
        
        // Asking user whether they want to add more workout data or not
        System.out.println("Workout added successfully!");
        System.out.println("Would you like to continue? (Y/N): ");
        String returnMenuOption = scanner.nextLine();
        while (!returnMenuOption.equalsIgnoreCase("y") && !returnMenuOption.equalsIgnoreCase("n")) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Would you like to continue? (Y/N): ");
            returnMenuOption = scanner.nextLine();
        }
        if (returnMenuOption.equalsIgnoreCase("Y")) {
            getWorkoutMenu();
        } else if (returnMenuOption.equalsIgnoreCase("N")) {
            getMenu();
        }
    }


    /**
     * Displays the view progress menu and handles
     * navigation through various options such as viewing today's calories,
     * workout data, meal breakdown, calorie summaries, and progress reports.
     */
    public static void getViewMenu() {
        // Print out the options for the menu
        System.out.println("=== View Progress ===");
        System.out.println("Select an option (1-11):");
        System.out.println("1. View Today's Calories");
        System.out.println("2. View Today's Workout");
        System.out.println("3. View Meal Breakdown");
        System.out.println("4. View Calories of a Particular Meal");
        System.out.println("5. View Average Calories Per Meal");
        System.out.println("6. View Calorie Consumption (Snacks/Foods)");
        System.out.println("7. View Workout Volume");
        System.out.println("8. View Heaviest Lift Per Exercise");
        System.out.println("9. View Calories Consumed vs. Goal");
        System.out.println("10. View Performance Summary");
        System.out.println("11. Back to Main Menu");

        // Initialize scanner and take user input
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        // Validate the user input to match one of the options
        while (!option.matches("[1-9]|10|11")) {
            System.out.println("Invalid option. Please select between 1-11:");
            option = scanner.nextLine();
        }

        // Call the appropriate function for the option chosen
        switch (option) {
            case "1":
                viewTodaysCalories();
                break;
            case "2":
                viewTodaysWorkout();
                break;
            case "3":
                viewMealBreakdown();
                break;
            case "4":
                viewCaloriesOfParticularMeal();
                break;
            case "5":
                viewAverageCaloriesPerMeal();
                break;
            case "6":
                viewCaloriesConsumptionSnacksAndFoods();
                break;
            case "7":
                viewVolumeOfWorkout();
                break;
            case "8":
                viewHeaviestLiftPerExercise();
                break;
            case "9":
                viewCaloriesConsumedVsGoal();
                break;
            case "10":
                viewPerformanceSummary();
                break;
            case "11":
                getMenu();
                return;
        }
        System.out.println("Returning to View Menu...");
        getViewMenu();
    }

    /**
     * Calculates and displays the total calories logged for the current day.
     * Displays a message if no calorie data is available.
     */
    private static void viewTodaysCalories() {
        // Retrieve the calorie data
        ArrayList<HashMap<String, Object>> calorieTrackingData = Calories.getData();

        // Check if its empty
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calorie data available for today.");
            return; // Go back to the view menu
        }

        // Go through all the array objects and print them out in correct format
        System.out.println("Today's Calories Tracking Data:");
        int totalCalories = 0;  // Stores the total of all the calories stored
        for (HashMap<String, Object> entry : calorieTrackingData) {
            System.out.println(". " + entry.get("name"));
            System.out.println("    Type:" + entry.get("snackOrMeal"));
            if (!entry.get("snackOrMeal").equals("snack")) {
                System.out.println("    Time:" + entry.get("mealType"));
            }
            System.out.println("    Calories:" + entry.get("calories") + " kcal");
            totalCalories += (int) entry.get("calories");   // Had to cast it to int for java to not show error
        }

        System.out.println("Today's Total Calories: " + totalCalories + " kcal\n");
    }

    /**
     * Displays the details of today's workout including workout plans,
     * exercise names, sets, weights, and repetitions.
     * Displays a message if no workout data is available.
     */
    private static void viewTodaysWorkout() {
        // Retrieve the workout data
        ArrayList<HashMap<String, Object>> workouts = Workout.getWorkouts();

        // Check if its empty, and leave the function if it is
        if (workouts.isEmpty()) {
            System.out.println("No workout data available for today.");
            return;
        }

        // Print out each workout stored
        workouts.forEach(workout -> {
            System.out.println("Workout Plan: " + workout.get("workoutPlan"));
            ArrayList<HashMap<String, Object>> exercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");
            exercises.forEach(exercise -> {
                System.out.println("Exercise: " + exercise.get("exerciseName"));
                ArrayList<HashMap<String, Integer>> sets = (ArrayList<HashMap<String, Integer>>) exercise.get("sets");
                sets.forEach(set -> System.out.println(" - " + set.get("weightLifted") + " kg x " + set.get("reps") + " reps"));
            });
        });
        System.out.println(" ");
    }

    /**
     * Displays the details of today's workout including workout plans,
     * exercise names, sets, weights, and repetitions.
     * Displays a message if no workout data is available.
     */
    private static void viewMealBreakdown() {
        // Retrieve the calorie data
        ArrayList<HashMap<String, Object>> calorieTrackingData = Calories.getData();

        // Check if data is present, if not then exit the function
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calorie data available for today.");
            return;
        }

        // These array lists will store the sorted data from the original stored data
        ArrayList<HashMap<String, Object>> snacks = new ArrayList<>();
        ArrayList<HashMap<String, Object>> breakfast = new ArrayList<>();
        ArrayList<HashMap<String, Object>> lunch = new ArrayList<>();
        ArrayList<HashMap<String, Object>> dinner = new ArrayList<>();

        // Store the data in the correct structure
        for (HashMap<String, Object> entry : calorieTrackingData) {
            String snackOrMeal = (String) entry.get("snackOrMeal");
            if (snackOrMeal.equals("snack")) {
                snacks.add(entry);
            } else {
                String mealType = (String) entry.get("mealType");
                switch (mealType) {
                    case "breakfast":
                        breakfast.add(entry);
                        break;
                    case "lunch":
                        lunch.add(entry);
                        break;
                    case "dinner":
                        dinner.add(entry);
                        break;
                }
            }
        }

        // Print out all the separate meal data separately
        System.out.println("Today, you had the following food items:");
        System.out.println("Breakfast: ");
        int totalBreakfast = 0;
        for (HashMap<String, Object> entry : breakfast) {
            System.out.println("Name: " + entry.get("name"));
            System.out.println("Calories: " + entry.get("calories"));
            totalBreakfast += (int) entry.get("calories");
        }
        System.out.println("Total calories in breakfast: " + totalBreakfast + "kcal");
        System.out.println(" ");

        System.out.println("Lunch: ");
        int totalLunch = 0;
        for (HashMap<String, Object> entry : lunch) {
            System.out.println("Name: " + entry.get("name"));
            System.out.println("Calories: " + entry.get("calories"));
            totalLunch += (int) entry.get("calories");
        }
        System.out.println("Total calories in lunch: " + totalLunch + "kcal");
        System.out.println(" ");

        System.out.println("Dinner: ");
        int totalDinner = 0;
        for (HashMap<String, Object> entry : dinner) {
            System.out.println("Name: " + entry.get("name"));
            System.out.println("Calories: " + entry.get("calories"));
            totalDinner += (int) entry.get("calories");
        }
        System.out.println("Total calories in dinner: " + totalDinner + "kcal");
        System.out.println(" ");

        System.out.println("Snacks: ");
        int totalSnacks = 0;
        for (HashMap<String, Object> entry : snacks) {
            System.out.println("Name: " + entry.get("name"));
            System.out.println("Calories: " + entry.get("calories"));
            totalSnacks += (int) entry.get("calories");
        }
        System.out.println("Total calories in snacks: " + totalSnacks + "kcal");
        System.out.println(" ");
    }

    /**
     * Enables the user to view calories of a specific meal or snack by name.
     * Filters and displays matching entries from the calorie tracking data.
     */
    private static void viewCaloriesOfParticularMeal() {
        // Retrieve the calorie data
        ArrayList<HashMap<String, Object>> calorieTrackingData = Calories.getData();

        // Check if empty and return if it is
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calories data found.");
            return;
        }

        // Take and validate the user input of which meal they chose to view
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the meal type (Breakfast, Lunch, Dinner, Snacks): ");
        String search = scanner.nextLine();
        while (!(search.equalsIgnoreCase("breakfast") || search.equalsIgnoreCase("lunch") || search.equalsIgnoreCase("dinner") || search.equalsIgnoreCase("snacks"))) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("Enter the meal type (Breakfast, Lunch, Dinner, Snacks): ):");
            search = scanner.nextLine();
        }

        // Depending on the search value, print out the right data
        System.out.println("==== " + search + " ====");
        int totalCalories = 0;
        for (HashMap<String, Object> entry : calorieTrackingData) {
            if (search.equalsIgnoreCase("snacks")) {
                if (entry.get("snackOrMeal").equals("snack")) {
                    totalCalories += (int) entry.get("calories");
                    System.out.println("1. " + entry.get("name"));
                    System.out.println("Calories:" + entry.get("calories"));
                }
            } else {
                if (entry.get("mealType").equals(search.toLowerCase())) {
                    System.out.println("1. " + entry.get("name"));
                    System.out.println("Calories:" + entry.get("calories"));
                    totalCalories += (int) entry.get("calories");
                }
            }
        }
        System.out.println("The total calories in " + search + ": " + totalCalories + " kcal\n");
    }

    /**
     * Displays the comparison between the total calories logged for the day
     * and the user's calorie consumption goal. Indicates the surplus or deficit of calories.
     */
    private static void viewCaloriesConsumedVsGoal() {
        // Retrieve tge calorie data
        ArrayList<HashMap<String, Object>> calorieTrackingData = Calories.getData();

        // If empty, return to View Menu
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calories data found.");
            return;
        }

        // Take and validate the input of the users calorie goal
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your goal for calories consumed today: ");
        String calorieGoalInput = scanner.nextLine();
        int calorieGoal = 0;
        boolean goalValid = false;
        while (!goalValid) {
            try {
                calorieGoal = Integer.parseInt(calorieGoalInput);
                goalValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // Find the total calories from the data
        int totalCalories = 0;
        for (HashMap<String, Object> entry : calorieTrackingData) {
            totalCalories += (int) entry.get("calories");
        }

        System.out.println("Calories Consumed: " + totalCalories + " kcal");
        System.out.println("Calorie Goal: " + calorieGoal + " kcal");

        // Compare the total calories to the goal entered by the user
        if (totalCalories > calorieGoal) {
            System.out.println("You exceeded your goal by " + (totalCalories - calorieGoal) + " kcal.\n");
        } else {
            System.out.println("You are under your goal by " + (calorieGoal - totalCalories) + " kcal.\n");
        }
    }

    /**
     * Calculates and displays the total workout volume for the day (weight x reps per set).
     * Summarizes the volume across all exercises and workouts.
     */
    private static void viewVolumeOfWorkout() {
        // Retrieve the workout data
        ArrayList<HashMap<String, Object>> workouts = Workout.getWorkouts();

        // If no data, do nothing
        if (workouts.isEmpty()) {
            System.out.println("No workouts found.");
        } else {
            // Go through each exercise, do the calculation and then print it out
            for (HashMap<String, Object> entry : workouts) {
                for (HashMap<String, Object> exercise : (ArrayList<HashMap<String, Object>>) entry.get("exercises")) {
                    System.out.println("Name of Exercise: " + exercise.get("exerciseName"));
                    int setCount = 0;
                    float volume = 1;
                    for (HashMap<String, Object> set : (ArrayList<HashMap<String, Object>>) exercise.get("sets")) {
                        setCount++;
                        volume *= (int) set.get("reps") *  (float) set.get("weightLifted");
                    }
                    volume *= setCount;
                    System.out.println("Volume: " + Math.round(volume) + " kg");
                }
            }
        }
        System.out.println(" ");
    }

    /**
     * Calculates and displays the average calorie intake per meal type (e.g., Snacks, Breakfast, etc.).
     */
    private static void viewAverageCaloriesPerMeal() {
        // Retrieve the calorie data
        ArrayList<HashMap<String, Object>> calorieTrackingData = Calories.getData();

        // If empty, return to View Menu
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calories data found.");
            return;
        }

        // Initialize all the variables needed, with the average variables storing the total as well
        float avgBreakfastCalories = 0f;
        int breakfastCount = 0;
        float avgLunchCalories = 0f;
        int lunchCount = 0;
        float avgDinnerCalories = 0f;
        int dinnerCount = 0;
        float avgSnackCalories = 0f;
        int snacksCount = 0;

        // Add to both count and total of the corresponding meal type
        for (HashMap<String, Object> entry : calorieTrackingData) {
            String mealType = (String) entry.get("mealType");
            String snackOrMeal = (String) entry.get("snackOrMeal");
            if (snackOrMeal.equals("snack")) {
                avgSnackCalories += (int) entry.get("calories");
                snacksCount++;
            } else {
                switch (mealType) {
                    case "breakfast":
                        avgBreakfastCalories += (int) entry.get("calories");
                        breakfastCount++;
                        break;
                    case "lunch":
                        avgLunchCalories += (int) entry.get("calories");
                        lunchCount++;
                        break;
                    case "dinner":
                        avgDinnerCalories += (int) entry.get("calories");
                        dinnerCount++;
                        break;
                }
            }
        }
        // Calculate the averages
        avgBreakfastCalories /= breakfastCount;
        avgLunchCalories /= lunchCount;
        avgDinnerCalories /= dinnerCount;
        avgSnackCalories /= snacksCount;

        // Output them
        System.out.println("Average Calories in Breakfast: " + avgBreakfastCalories + " kcal");
        System.out.println("Average Calories in Lunch: " + avgLunchCalories + " kcal");
        System.out.println("Average Calories in Dinner: " + avgDinnerCalories + " kcal");
        System.out.println("Average Calories in Snacks: " + avgSnackCalories + " kcal\n");
    }

    /**
     * Displays the total calories consumed from snacks and meals separately
     * for the current day.
     */
    private static void viewCaloriesConsumptionSnacksAndFoods() {
        // Retrieve calorie data
        ArrayList<HashMap<String, Object>> calorieTrackingData = Calories.getData();

        // If empty, return to View Menu
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calories data found.");
            return;
        }

        // Find the total calories of both snacks and meals
        int snackCalories = 0;
        int mealCalories = 0;
        for (HashMap<String, Object> entry : calorieTrackingData) {
            String snackOrMeal = (String) entry.get("snackOrMeal");
            if (snackOrMeal.equals("snack")) {
                snackCalories += (int) entry.get("calories");
            } else {
                mealCalories += (int) entry.get("calories");
            }
        }

        // Calculate the percentage of each consumed
        float percentageSnack = ((float) snackCalories / (mealCalories + snackCalories)) * 100;
        float percentageMeal = ((float) mealCalories / (mealCalories + snackCalories)) * 100;

        // Output the calculated data
        System.out.println("Total Snack Calories: " + snackCalories + " kcal");
        System.out.println("Total Meal Calories: " + mealCalories + " kcal");
        System.out.printf("Percentage Of Calories From Snacks: %.2f%%\n", percentageSnack);
        System.out.printf("Percentage Of Calories From Meals: %.2f%%\n", percentageMeal);
        System.out.println(" ");
    }

    /**
     * Calculates and displays the heaviest weight lifted for
     * each exercise in all recorded workouts.
     */
    private static void viewHeaviestLiftPerExercise() {
        // Retrieve the data
        ArrayList<HashMap<String, Object>> workouts = Workout.getWorkouts();

        // Check if empty
        if (workouts.isEmpty()) {
            System.out.println("No workouts found.");
            return;
        }

        // Go through each exercise, and print out the max weight lifted for that exercise
        for (HashMap<String, Object> entry : workouts) {
            for (HashMap<String, Object> exercise : (ArrayList<HashMap<String, Object>>) entry.get("exercises")) {
                System.out.println("Name: " + exercise.get("exerciseName"));
                float maxWeightLift = 0f;
                for (HashMap<String, Object> set : (ArrayList<HashMap<String, Object>>) exercise.get("sets")) {
                    float currentWeightLift = (float) set.get("weightLifted");
                    if (maxWeightLift < currentWeightLift) {
                        maxWeightLift = (float) set.get("weightLifted");
                    }
                }
                System.out.println("Max Weight Lifted : " + maxWeightLift + " kg");
            }
        }
        System.out.println(" ");
    }

    /**
     * Provides a summary of performance including today's calories,
     * workout volume, and calories consumed vs. the goal.
     * Combines data from individual viewing functions.
     */
    private static void viewPerformanceSummary() {
        // Call all the functions needed to show the data
        System.out.println("=== Performance Summary ===");
        viewTodaysCalories();
        viewCaloriesConsumedVsGoal();
        System.out.println("\nWorkouts Performed: ");
        viewVolumeOfWorkout();
    }
}