import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    // Shared variables across functions
    private static ArrayList<HashMap<String, String>> calorieTrackingData = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> workouts = new ArrayList<>();
    private static int calorieGoal = 2000; // Default calorie goal

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
                return; // Exit the application
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
        while (!snackOrMeal.toLowerCase().equals("snack") && !snackOrMeal.toLowerCase().equals("meal")) {
            System.out.println("Invalid input. Please try again.");
            System.out.print("Are you having a snack or a meal?: ");
            snackOrMeal = scanner.nextLine();
        }

        // If it's a meal, ask for the specific type
        String mealType = "";
        if (snackOrMeal.equalsIgnoreCase("meal")) {
            System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
            mealType = scanner.nextLine();

            while (!mealType.toLowerCase().equals("breakfast") && !mealType.toLowerCase().equals("lunch")
                    && !mealType.toLowerCase().equals("dinner")) {
                System.out.println("Invalid input. Please try again.");
                System.out.print("What type of meal is it? (Breakfast/Lunch/Dinner): ");
                mealType = scanner.nextLine();
            }
        }

        // Ask for the food's name
        System.out.print("Enter the name of the food: ");
        String foodName = scanner.nextLine();

        while (!foodName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Food must contain alphabetic characters.");
            System.out.print("Enter the name of the food: ");
            foodName = scanner.nextLine();
        }

        // Ask for calorie amount with validation
        String caloriesInput = "";
        boolean isCaloriesInputValid = false;
        int calories = 0;

        while (!isCaloriesInputValid) {
            System.out.print("Enter the number of calories: ");
            caloriesInput = scanner.nextLine();
            try {
                calories = Integer.parseInt(caloriesInput);
                if (calories < 0 || calories > 20000) {
                    System.out.println("Invalid input. Number must be in the range 0-20,000. Please try again.");
                } else {
                    isCaloriesInputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid whole number.");
            }
        }

        // Log the calorie data
        HashMap<String, String> calorieEntry = new HashMap<>();
        calorieEntry.put("snackOrMeal", snackOrMeal);
        calorieEntry.put("mealType", snackOrMeal.equals("meal") ? mealType : "Snack");
        calorieEntry.put("mealName", foodName);
        calorieEntry.put("calories", String.valueOf(calories));
        calorieTrackingData.add(calorieEntry);

        System.out.println("Calorie entry added successfully!");
        getMenu();
    }

    /**
     * Displays the workout menu, collects details about the workout plan,
     * validates the input, gathers exercises, sets and repetitions, and
     * stores them in the workout tracking list.
     */
    public static void getWorkoutMenu() {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the workout plan
        System.out.print("What workout plan are you following today (Push/Pull/Leg/Upper/Lower)?: ");
        String workoutPlan = scanner.nextLine();

        // Validate the workout plan
        while (!Workout.validateWorkoutPlan(workoutPlan)) {
            System.out.print("Invalid input. Enter a valid workout plan (Push/Pull/Leg/Upper/Lower): ");
            workoutPlan = scanner.nextLine();
        }

        // Ask for the number of exercises
        boolean isExerciseCountValid = false;
        int exerciseCount = 0;
        while (!isExerciseCountValid) {
            System.out.print("How many exercises have you done?: ");
            String input = scanner.nextLine();
            try {
                exerciseCount = Integer.parseInt(input);
                if (exerciseCount > 10) {
                    System.out.println("Cannot input more than 10 exercises. Try again.");
                } else {
                    isExerciseCountValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }

        // Collect workout exercises
        ArrayList<HashMap<String, Object>> exercises = new ArrayList<>();
        for (int i = 0; i < exerciseCount; i++) {
            System.out.print("Enter the name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            // Validate sets count
            boolean isSetCountValid = false;
            int setCount = 0;
            while (!isSetCountValid) {
                System.out.print("Enter the number of sets for exercise " + (i + 1) + ": ");
                String input = scanner.nextLine();
                try {
                    setCount = Integer.parseInt(input);
                    if (setCount > 5) {
                        System.out.println("Cannot input more than 5 sets. Try again.");
                    } else {
                        isSetCountValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a valid number.");
                }
            }

            ArrayList<HashMap<String, Integer>> sets = new ArrayList<>();
            for (int j = 0; j < setCount; j++) {
                System.out.print("Enter the weight lifted for set " + (j + 1) + " in kg: ");
                int weight = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter the number of reps for set " + (j + 1) + ": ");
                int reps = Integer.parseInt(scanner.nextLine());

                HashMap<String, Integer> setRecord = new HashMap<>();
                setRecord.put("weight", weight);
                setRecord.put("reps", reps);
                sets.add(setRecord);
            }

            HashMap<String, Object> exerciseRecord = new HashMap<>();
            exerciseRecord.put("exerciseName", exerciseName);
            exerciseRecord.put("sets", sets);
            exercises.add(exerciseRecord);
        }

        // Store all workout data
        HashMap<String, Object> workout = new HashMap<>();
        workout.put("workoutPlan", workoutPlan);
        workout.put("exercises", exercises);
        workouts.add(workout);

        System.out.println("Workout data recorded successfully!");
        getMenu();
    }

    /**
     * Displays the view progress menu and handles
     * navigation through various options such as viewing today's calories,
     * workout data, meal breakdown, calorie summaries, and progress reports.
     */
    public static void getViewMenu() {
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

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!option.matches("[1-9]|10|11")) {
            System.out.println("Invalid option. Please select between 1-11:");
            option = scanner.nextLine();
        }

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
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calorie data available for today.");
            return;
        }

        int totalCalories = calorieTrackingData.stream()
                .mapToInt(entry -> Integer.parseInt(entry.get("calories")))
                .sum();
        System.out.println("Today's Total Calories: " + totalCalories + " kcal");
    }

    /**
     * Displays the details of today's workout including workout plans,
     * exercise names, sets, weights, and repetitions.
     * Displays a message if no workout data is available.
     */
    private static void viewTodaysWorkout() {
        if (workouts.isEmpty()) {
            System.out.println("No workout data available for today.");
            return;
        }

        workouts.forEach(workout -> {
            System.out.println("Workout Plan: " + workout.get("workoutPlan"));
            @SuppressWarnings("unchecked")
            ArrayList<HashMap<String, Object>> exercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");
            exercises.forEach(exercise -> {
                System.out.println("Exercise: " + exercise.get("exerciseName"));
                @SuppressWarnings("unchecked")
                ArrayList<HashMap<String, Integer>> sets = (ArrayList<HashMap<String, Integer>>) exercise.get("sets");
                sets.forEach(set -> System.out.println(" - " + set.get("weight") + " kg x " + set.get("reps") + " reps"));
            });
        });
    }

    /**
     * Displays the details of today's workout including workout plans,
     * exercise names, sets, weights, and repetitions.
     * Displays a message if no workout data is available.
     */
    private static void viewMealBreakdown() {
        long snacks = calorieTrackingData.stream().filter(meal -> "Snack".equals(meal.get("mealType"))).count();
        long breakfast = calorieTrackingData.stream().filter(meal -> "Breakfast".equals(meal.get("mealType"))).count();
        long lunch = calorieTrackingData.stream().filter(meal -> "Lunch".equals(meal.get("mealType"))).count();
        long dinner = calorieTrackingData.stream().filter(meal -> "Dinner".equals(meal.get("mealType"))).count();

        System.out.println("Snacks: " + snacks);
        System.out.println("Breakfasts: " + breakfast);
        System.out.println("Lunches: " + lunch);
        System.out.println("Dinners: " + dinner);
    }

    /**
     * Enables the user to view calories of a specific meal or snack by name.
     * Filters and displays matching entries from the calorie tracking data.
     */
    private static void viewCaloriesOfParticularMeal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the meal type or snack name: ");
        String search = scanner.nextLine();

        calorieTrackingData.stream()
                .filter(meal -> meal.get("mealType").equalsIgnoreCase(search) || meal.get("mealName").equalsIgnoreCase(search))
                .forEach(meal -> System.out.println(meal.get("mealName") + ": " + meal.get("calories") + " kcal"));
    }

    /**
     * Displays the comparison between the total calories logged for the day
     * and the user's calorie consumption goal. Indicates the surplus or deficit of calories.
     */
    private static void viewCaloriesConsumedVsGoal() {
        int totalCalories = calorieTrackingData.stream()
                .mapToInt(meal -> Integer.parseInt(meal.get("calories")))
                .sum();

        System.out.println("Calories Consumed: " + totalCalories + " kcal");
        System.out.println("Calorie Goal: " + calorieGoal + " kcal");

        if (totalCalories > calorieGoal) {
            System.out.println("You exceeded your goal by " + (totalCalories - calorieGoal) + " kcal.");
        } else {
            System.out.println("You are under your goal by " + (calorieGoal - totalCalories) + " kcal.");
        }
    }

    private static void viewVolumeOfWorkout() {
        int totalVolume = workouts.stream().mapToInt(workout -> {
            @SuppressWarnings("unchecked")
            ArrayList<HashMap<String, Object>> exercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");

            return exercises.stream()
                    .mapToInt(exercise -> {
                        @SuppressWarnings("unchecked")
                        ArrayList<HashMap<String, Integer>> sets = (ArrayList<HashMap<String, Integer>>) exercise.get("sets");

                        return sets.stream()
                                .mapToInt(set -> set.get("weight") * set.get("reps"))
                                .sum();
                    }).sum();
        }).sum();

        System.out.println("Total Workout Volume: " + totalVolume + " kg");
    }

    private static void viewAverageCaloriesPerMeal() {
        Map<String, Double> averageCalories = calorieTrackingData.stream()
                .collect(Collectors.groupingBy(
                        meal -> meal.get("mealType"),
                        Collectors.averagingInt(meal -> Integer.parseInt(meal.get("calories")))
                ));
        averageCalories.forEach((mealType, avg) -> System.out.printf("%s: %.2f kcal%n", mealType, avg));
    }

    private static void viewCaloriesConsumptionSnacksAndFoods() {
        int snackCalories = calorieTrackingData.stream()
                .filter(meal -> "Snack".equals(meal.get("mealType")))
                .mapToInt(meal -> Integer.parseInt(meal.get("calories")))
                .sum();

        int mealCalories = calorieTrackingData.stream()
                .filter(meal -> !"Snack".equals(meal.get("mealType")))
                .mapToInt(meal -> Integer.parseInt(meal.get("calories")))
                .sum();

        System.out.println("Total Snack Calories: " + snackCalories + " kcal");
        System.out.println("Total Meal Calories: " + mealCalories + " kcal");
    }

    private static void viewHeaviestLiftPerExercise() {
        workouts.stream()
                .flatMap(workout -> {
                    @SuppressWarnings("unchecked")
                    ArrayList<HashMap<String, Object>> exercises = (ArrayList<HashMap<String, Object>>) workout.get("exercises");
                    return exercises.stream();
                })
                .forEach(exercise -> {
                    String exerciseName = (String) exercise.get("exerciseName");

                    @SuppressWarnings("unchecked")
                    ArrayList<HashMap<String, Integer>> sets = (ArrayList<HashMap<String, Integer>>) exercise.get("sets");
                    int maxWeight = sets.stream()
                            .mapToInt(set -> set.get("weight"))
                            .max()
                            .orElse(0);

                    System.out.println(exerciseName + " Heaviest Lift: " + maxWeight + " kg");
                });
    }

    private static void viewPerformanceSummary() {
        System.out.println("=== Performance Summary ===");
        viewTodaysCalories();
        viewVolumeOfWorkout();
        viewCaloriesConsumedVsGoal();
    }
}