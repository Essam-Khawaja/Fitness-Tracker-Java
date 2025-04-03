package MainFiles;

import Data.Calories;
import Data.Exercise;
import Data.User;
import Data.Set;
import Data.Workout;
import Enums.MealTime;
import Enums.MealType;
import Enums.WorkoutPlan;
import Save.Save;

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
    private static User user = new User("", "", "");    // This is the user of the menu

    /**
     * This is where the app starts from, by asking the user to sign up or log in
     */
    public static void startApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✨ WELCOME TO YOUR FITNESS JOURNEY ✨");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🌟 Please choose an option to continue: 🌟");
        System.out.println("1️⃣ Sign Up - Join us and step into your fitness journey! ✍️");
        System.out.println("2️⃣ Log In - Welcome back, let's get moving! 🔑");
        System.out.println("---------------------------------------");

        String choice = scanner.nextLine();     // Takes the input
        // Validates the input
        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("🚨 Invalid input! Please enter 1️⃣ or 2️⃣.");
            choice = scanner.nextLine();
        }

        // Corresponding method called from choice
        if (choice.equals("1")) {
            handleSignUp();
        } else {
            handleLogin();
        }
        getMenu();
        return;
    }

    public static void commandLineStartApp(String path) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✨ Welcome! File detected. 🌟");
        System.out.println("📂 We will load your data after you log in. 📂");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Scanner scanner = new Scanner(System.in);
        // Take the inputs
        System.out.println("🔑 Login:");
        System.out.print("📧 Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();


        user.setEmail(email);
        user.setPassword(password);

        int userExists = Save.ValidateUser(user);   // Check if password or email is correct

        while (userExists == 0 || userExists == -1) {
            if (userExists == 0) {      // If password is wrong
                System.out.println("🚨 Invalid password! Please try again.");
            } else {
                System.out.println("🚨 Invalid email! Please try again.");
            }
            System.out.print("📧 Enter Email: ");
            email = scanner.nextLine();

            System.out.print("🔒 Enter Password: ");
            password = scanner.nextLine();

            user.setEmail(email);
            user.setPassword(password);

            userExists = Save.ValidateUser(user);
        }
        Save.LoadData(user, path);
        System.out.println("\n🎉 Welcome! You are successfully logged in and your data has been loaded! 🎉");
        System.out.println("---------------------------------------");
        getMenu();
        return;
    }

    /**
     * Handles the sign-up for the user
     */
    private static void handleSignUp() {
        Scanner scanner = new Scanner(System.in);
        // Take all the data in
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✨ CREATE A NEW ACCOUNT ✨");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("📧 Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("👤 Enter Username: ");
        String username = scanner.nextLine();

        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        int userExists = Save.ValidateUser(user);   // Check if a user with same email exists
        while (userExists == 0) {
            // Warn and check whether the user wants to login instead
            System.out.println("🚨 This user already exists!");
            System.out.println("🔁 Do you want to log in instead? (Y/N)");
            String choice = scanner.nextLine();

            while (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("🚨 Invalid input! Please enter ✅ Y or ❌ N.");
                choice = scanner.nextLine();
            }

            // Try signing in again
            if (choice.equals("Y")) {
                System.out.println("\n🔁 SIGN UP AGAIN:");
                System.out.print("📧 Enter Email: ");
                email = scanner.nextLine();

                System.out.print("🔒 Enter Password: ");
                password = scanner.nextLine();

                System.out.print("👤 Enter Username: ");
                username = scanner.nextLine();

                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(username);
                userExists = Save.ValidateUser(user);
            } else {    // Otherwise go back to start app
                startApp();
                return;
            }
        }
        // Confirm we have the right details
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        Save.SaveNewUser(user);     // Save the new user
        System.out.println("\n🎊 Account created successfully! You're all set to begin! 🎊");
        System.out.println("---------------------------------------");
    }

    /**
     * Handles the login of the user
     */
    private static void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        // Take the inputs
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✨ LOG IN TO YOUR ACCOUNT ✨");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("📧 Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();

        user.setEmail(email);
        user.setPassword(password);

        int userExists = Save.ValidateUser(user);   // Check if password or email is correct

        while (userExists == 0 || userExists == -1) {
            if (userExists == 0) {      // If password is wrong, take input again
                System.out.println("🚨 Invalid password! Please try again.");
                System.out.print("📧 Enter Email: ");
                email = scanner.nextLine();

                System.out.print("🔒 Enter Password: ");
                password = scanner.nextLine();

                user.setEmail(email);
                user.setPassword(password);

                userExists = Save.ValidateUser(user);
            } else {    // If email is wrong, then ask whether they want to sign up instead
                System.out.println("🚨 Email does not exist.");
                System.out.println("🔁 Would you like to sign up instead? (Y/N)");
                String choice = scanner.nextLine();

                while (!choice.equals("Y") && !choice.equals("N")) {
                    System.out.println("🚨 Invalid input! Please enter ✅ Y or ❌ N.");
                    choice = scanner.nextLine();
                }

                if (choice.equals("Y")) {
                    handleSignUp();
                    return;
                } else {
                    startApp();
                    return;
                }
            }
        }
        // If we have both correct email and password
        if (userExists == 1) {
            System.out.println("📂 Do you want to load your previously saved data? (Y/N)");
            String choice = scanner.nextLine();
            while (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("🚨 Invalid input! Please enter ✅ Y or ❌ N.");
                choice = scanner.nextLine();
            }
            if (choice.equals("Y")) {
                Save.LoadData(user, "src/Save/Save.csv");    // Load the data
                System.out.println("\n📈 Your data has been successfully loaded!");
            }
            System.out.println("\n🎉 Login successful! Welcome back! 🎉");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }
    /**
     * Displays the main menu and handles user input for
     * navigating between options such as calorie tracking,
     * workout tracking, viewing progress, or exiting the program.
     */
    public static void getMenu() {
        // Displaying the main menu
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✨         🌟 MAIN MENU 🌟         ✨");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("          TRACK YOUR GOALS         ");
        System.out.println("===================================");
        System.out.println(" Please choose from the following options:");
        System.out.println("   🍎 1. Calorie Tracking");
        System.out.println("   🏋️ 2. Workout Tracking");
        System.out.println("   📊 3. View Progress");
        System.out.println("   💾 4. Save Data");
        System.out.println("   ❌ 5. Exit");
        System.out.println("===================================");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        // Validate the user's input
        while (!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5"))){
            System.out.println("🚫 Invalid choice! Please choose a number between 1️⃣ and 5️⃣.");
            option = scanner.nextLine();
        }

        // Handle the options
        System.out.println("🔍 Processing your choice...\n");
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
                Save.SaveData(user);
                System.out.println("Data saved successfully!");
                getMenu();
                break;
            case "5":
                System.out.println("Exiting the program...");
                return;
        }
    }

    /**
     * Displays the calorie menu, collects details about a snack or meal,
     * validates the input, logs the data, and stores it in the calorie tracking list.
     * Prompts the user to specify the type of meal, name, and calorie count.
     */
    public static void getCalorieMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🍎         CALORIE TRACKING         🥗");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" Please answer the following questions below:");
        System.out.println("-----------------------------");

        // Validate input
        String snackOrMealInput = "";
        while (true) {
            System.out.println("What are you having: ");
            System.out.println("1. Snack");
            System.out.println("2. Meal");
            snackOrMealInput = scanner.nextLine();
            if (snackOrMealInput.equalsIgnoreCase("1") || snackOrMealInput.equalsIgnoreCase("2")) {
                break;
            }
            System.out.println("🚫 Invalid input! Please enter 'snack' or 'meal'.");
        }
        MealType snackOrMeal = null;
        if (snackOrMealInput.equalsIgnoreCase("1")){
            snackOrMeal = MealType.SNACK;
        } else {
            snackOrMeal = MealType.MEAL;
        }

        // If it's a meal, ask for the specific type
        String mealTypeInput = "";
        if (snackOrMeal.equals(MealType.MEAL)) {
            System.out.println("\n What type of meal are you tracking?");
            System.out.println("1. Breakfast");
            System.out.println("2. Lunch");
            System.out.println("3. Dinner");
            mealTypeInput = scanner.nextLine();

            while (!mealTypeInput.equalsIgnoreCase("1") && !mealTypeInput.equalsIgnoreCase("2")
                    && !mealTypeInput.equalsIgnoreCase("3")) {
                System.out.println("Invalid response! Choose from 'Breakfast', 'Lunch', or 'Dinner'.");
                System.out.println("What type of meal are you tracking?");
                System.out.println("1. Breakfast");
                System.out.println("2. Lunch");
                System.out.println("3. Dinner");
                mealTypeInput = scanner.nextLine();
            }
        }
        MealTime mealType = MealTime.NULL;
        switch (mealTypeInput) {
            case "1":
                mealType = MealTime.BREAKFAST;
                break;
            case "2":
                mealType = MealTime.LUNCH;
                break;
            case "3":
                mealType = MealTime.DINNER;
                break;
        }

        // Ask for the food's name
        System.out.print("\n Enter the name of the food item: ");
        String foodName = scanner.nextLine();

        // Ask for calorie amount with validation
        String caloriesInput;
        boolean isCaloriesInputValid = false;
        int calories = 0;

        while (!isCaloriesInputValid) {
            System.out.print(" How many calories does this item have? (Enter a number): ");
            caloriesInput = scanner.nextLine().trim();
            try {
                calories = Integer.parseInt(caloriesInput);
                if (calories >= 0 && calories <= MAX_CALORIES) {
                    isCaloriesInputValid = true;
                } else {
                    System.out.println("🚫 Invalid input! Calories must be between 0 and " + MAX_CALORIES + ". Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("🚫 Invalid input! Please enter a valid numeric value.");
            }
        }

        // Log the calorie data
        Calories newCalorieData = new Calories(snackOrMeal, mealType, foodName, calories);
        user.addCalorieData(newCalorieData);

        // Check if the user wants to continue, with validation
        System.out.println("\n✅━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━✅");
        System.out.println("    🎉 SUCCESS! CALORIE ENTRY ADDED 🎉");
        System.out.println("✅━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━✅");

        System.out.print(" ➕ Would you like to add another entry? (Y/N): ");
        String returnMenuOption = ""; // Declare and initialize returnMenuOption
        while (true) {
            returnMenuOption = scanner.nextLine();
            if (returnMenuOption.equalsIgnoreCase("Y")) {
                getCalorieMenu();
                break;
            } else if (returnMenuOption.equalsIgnoreCase("N")) {
                getMenu();
                break;
            } else {
                System.out.println("🚫 Invalid input. Please enter 'Y' or 'N': ");
                System.out.print(" ➕ Would you like to add another entry? (Y/N): ");
            }
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
        System.out.println("\n 🏋️ Choose your workout plan for today!: ");
        System.out.println("1. Push 🤜");
        System.out.println("2. Pull 🤛");
        System.out.println("3. Leg 🦵");
        System.out.println("4. Upper 🏋️‍♂");
        System.out.println("5. Lower ⬇️");
        System.out.println("6. Full Body 💪");

        String workoutPlanInput = scanner.nextLine();

        boolean isWorkoutValid = Workout.validateWorkoutPlan(workoutPlanInput);

        while (!isWorkoutValid) {
            System.out.println("\n 🏋️ Choose your workout plan for today!: ");
            System.out.println(" 1. Push 🤜");
            System.out.println(" 2. Pull 🤛");
            System.out.println(" 3. Leg 🦵");
            System.out.println(" 4. Upper 🏋️‍♂");
            System.out.println(" 5. Lower ⬇️");
            System.out.println(" 6. Full Body 💪");
            workoutPlanInput = scanner.nextLine();
            isWorkoutValid = Workout.validateWorkoutPlan(workoutPlanInput);
        }

        WorkoutPlan workoutPlan = null;
        switch (workoutPlanInput) {
            case "1":
                workoutPlan = WorkoutPlan.PUSH;
                break;
            case "2":
                workoutPlan = WorkoutPlan.PULL;
                break;
            case "3":
                workoutPlan = WorkoutPlan.LEGS;
                break;
            case "4":
                workoutPlan = WorkoutPlan.UPPER;
                break;
            case "5":
                workoutPlan = WorkoutPlan.LOWER;
                break;
            case "6":
                workoutPlan = WorkoutPlan.FULL_BODY;
                break;
        }

        // Ask for the number of exercises with validation
        boolean isExerciseValid  = false;
        int exercisesCount = 0;     // Initialize exerciseCount to 0
        while (!isExerciseValid) {
            System.out.print("\n 🗓️ How many exercises have you performed today? (Enter a number ✏️): ");
            try {
                String exercisesCountInput = scanner.nextLine();
                exercisesCount = Integer.parseInt(exercisesCountInput);
                // Check to see if the user entered more than the max number of exercises to be stored
                if (exercisesCount > MAX_EXERCISE_NUMBER) {
                    System.out.println("Invalid input! You cannot log more than 10 exercises. Try again.");
                } else {
                    isExerciseValid = true;
                }
            } catch (NumberFormatException e) {     // Check to see if the user did not enter a number
                System.out.println("Invalid input. Can only be a number. Please try again.");
            }
        }

        // This will later be stored in the workouts variable
        ArrayList<Exercise> exercises = new ArrayList<>();

        // Loop through exercise count times
        for (int i = 0; i < exercisesCount; i++) {
            // Input details for each exercise
            System.out.print("\n Enter name of exercise " + (i + 1) + ": ");
            String exerciseName = scanner.nextLine();

            // Will be stored in exercise, must be initialised each new exercise
            ArrayList<Set> sets = new ArrayList<>();

            // Input number of sets with validation
            boolean isSetValid  = false;
            int setsCount = 0;     // Initialize setsCount to 0
            while (!isSetValid) {
                System.out.print(" How many sets did you perform: ");
                String setsCountInput = scanner.nextLine();
                try {
                    setsCount = Integer.parseInt(setsCountInput);
                    // Check to see if the user entered more than the max number of sets to be stored
                    if (setsCount > MAX_SET_NUMBER) {
                        System.out.println("Invalid input! You cannot log more than 5 sets. Try again.");
                    } else {
                        isSetValid = true;
                    }
                } catch (NumberFormatException e) {     // Check to see if the user did not enter a number
                    System.out.println("Invalid input. Can only be a number. Please try again.");
                }
            }
            for (int j = 0; j < setsCount; j++) {
                // Input details for each set
                System.out.print(" Enter the weight lifted for set " + (j + 1) + " (in kg): ");
                String weightLiftedInput = scanner.nextLine();
                boolean isWeightLiftedValid = Workout.validateWeightLifted(weightLiftedInput);
                while (!isWeightLiftedValid) {
                    System.out.print(" Enter the weight lifted for set " + (j + 1) + " (in kg): ");
                    weightLiftedInput = scanner.nextLine();
                    isWeightLiftedValid = Workout.validateWeightLifted(weightLiftedInput);
                }
                Float weightLifted = Float.parseFloat(weightLiftedInput);

                System.out.print(" Enter the number of reps for set " + (j + 1) + ": ");
                String repsInput = scanner.nextLine();
                boolean isRepsValid = Workout.validateReps(repsInput);

                while (!isRepsValid) {
                    System.out.print("Enter number of reps for set " + (j + 1) + ": ");
                    repsInput = scanner.nextLine();
                    isRepsValid = Workout.validateReps(repsInput);
                }

                int reps = Integer.parseInt(repsInput);
                Set newSet = new Set(reps, weightLifted);
                sets.add(newSet);
            }

            // Create new exercise
            Exercise exercise = new Exercise(exerciseName, sets);
            exercises.add(exercise);
        }

        Workout newWorkoutData = new Workout(workoutPlan, exercises);
        user.addWorkoutData(newWorkoutData);

        System.out.println("\n---------------------------------");
        System.out.println("\n✅━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━✅");
        System.out.println("    🎉 SUCCESS! WORKOUT ENTRY ADDED 🎉");
        System.out.println("✅━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━✅");

        // Asking user whether they want to add more workout data or not
        System.out.println("🎉 Workout added successfully!");
        System.out.print(" ➕ Would you like to continue logging workouts? (Y/N): ");
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
        System.out.println("\n📊━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━📊");
        System.out.println("              VIEW PROGRESS");
        System.out.println("📊━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━📊");

        System.out.println(" 🗂️ Please select an option below: (1️⃣ - 1️⃣1️⃣):");
        System.out.println("1️⃣ View Today's Calories 📅");
        System.out.println("2️⃣ View Today's Workout 🏋️");
        System.out.println("3️⃣ View Meal Breakdown 🍽️");
        System.out.println("4️⃣ View Calories of a Particular Meal 🍛");
        System.out.println("5️⃣ View Average Calories Per Meal ⚖️");
        System.out.println("6️⃣ View Calorie Consumption (Snacks/Foods) 🥗");
        System.out.println("7️⃣ View Workout Volume 🏋️‍♂️");
        System.out.println("8️⃣ View Heaviest Lift Per Exercise 🏋️‍♀️");
        System.out.println("9️⃣ View Calories Consumed vs. Goal 🎯");
        System.out.println("🔟 View Performance Summary 📊");
        System.out.println("1️⃣1️⃣ Back to MainFiles.Main MainFiles.Menu 🔙");

        System.out.println("\n---------------------------------");
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
        System.out.println("Returning to View MainFiles.Menu...");
        getViewMenu();
    }

    private static void viewMealBreakdown() {
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();

        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calorie data available for today.");
            return;
        }

        ArrayList<Calories> snacks = new ArrayList<>();
        ArrayList<Calories> breakfast = new ArrayList<>();
        ArrayList<Calories> lunch = new ArrayList<>();
        ArrayList<Calories> dinner = new ArrayList<>();

        for (Calories data : calorieTrackingData) {
            if (data.getSnackOrMeal() == MealType.SNACK) {
                snacks.add(data);
            } else {
                switch (data.getMealTime()) {
                    case BREAKFAST -> breakfast.add(data);
                    case LUNCH -> lunch.add(data);
                    case DINNER -> dinner.add(data);
                }
            }
        }

        System.out.println("🍳 Breakfast:");
        int totalBreakfast = 0;
        for (Calories data : breakfast) {
            System.out.println("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal");
            totalBreakfast += data.getCalories();
        }
        System.out.println("  Total: " + totalBreakfast + " kcal");

        System.out.println("\n🥪 Lunch:");
        int totalLunch = 0;
        for (Calories data : lunch) {
            System.out.println("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal");
            totalLunch += data.getCalories();
        }
        System.out.println("  Total: " + totalLunch + " kcal");

        System.out.println("\n🍝 Dinner:");
        int totalDinner = 0;
        for (Calories data : dinner) {
            System.out.println("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal");
            totalDinner += data.getCalories();
        }
        System.out.println("  Total: " + totalDinner + " kcal");

        System.out.println("\n🍇 Snacks:");
        int totalSnacks = 0;
        for (Calories data : snacks) {
            System.out.println("  - " + data.getFoodName() + ": " + data.getCalories() + " kcal");
            totalSnacks += data.getCalories();
        }
        System.out.println("  Total: " + totalSnacks + " kcal");
    }

    /**
     * Calculates and displays the total calories logged for the current day.
     * Displays a message if no calorie data is available.
     */
    private static void viewTodaysCalories() {
        // Retrieve the calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();

        // Check if its empty
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calorie data available for today.");
            return; // Go back to the view menu
        }

        // Go through all the array objects and print them out in correct format
        System.out.println("\n===================================");
        System.out.println("   TODAY'S CALORIES TRACKING DATA  ");
        System.out.println("===================================");
        int totalCalories = 0;  // Stores the total of all the calories stored
        for (Calories data : calorieTrackingData) {
            System.out.println(". " + data.getFoodName());
            System.out.println("    Type:" + data.getSnackOrMeal());
            if (!(data.getSnackOrMeal() == MealType.SNACK)) {
                System.out.println("    Time:" + data.getMealTime());
            }
            System.out.println("    Calories:" + data.getCalories() + " kcal");
            totalCalories += data.getCalories();
        }
        System.out.println("🔥 Today's Total Calories: " + totalCalories + " kcal 🔥\n");
    }

    /**
     * Displays the details of today's workout including workout plans,
     * exercise names, sets, weights, and repetitions.
     * Displays a message if no workout data is available.
     */
    private static void viewTodaysWorkout() {
        System.out.println("\n===================================");
        System.out.println("💪      TODAY'S WORKOUT DATA       💪");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        ArrayList<Workout> workouts = user.getWorkoutData();

        // Check if its empty, and leave the function if it is
        if (workouts.isEmpty()) {
            System.out.println("No workout data available for today.");
            return;
        }

        // Print out each workout stored
        workouts.forEach(workout -> {
            System.out.println("🏋️ Data.Workout Plan: ✨ " + workout.getWorkoutPlan() + " ✨");
            ArrayList<Exercise> exercises = workout.getExercises();
            exercises.forEach(exercise -> {
                System.out.println("Exercise: " + exercise.getExerciseName());
                ArrayList<Set> sets = exercise.getSets();
                sets.forEach(set -> {
                    System.out.println(" - " + set.getWeightLifted() + "kg x " + set.getReps() + " reps");
                });
            });
        });
        System.out.println(" ");
    }

    /**
     * Filters and displays matching entries from the calorie tracking data.
     */
    private static void viewCaloriesOfParticularMeal() {
        // Retrieve the calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();

        // Check if empty and return if it is
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calories data found.");
            return;
        }

        // Take and validate the user input of which meal they chose to view
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n What type of meal do you want to view?");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("4. Snacks");
        String mealTypeInput = scanner.nextLine();

        while (!mealTypeInput.equalsIgnoreCase("1") && !mealTypeInput.equalsIgnoreCase("2")
                && !mealTypeInput.equalsIgnoreCase("3") && !mealTypeInput.equalsIgnoreCase("4")) {
            System.out.println("Invalid response!.");
            System.out.println("What type of meal are you tracking?");
            System.out.println("1. Breakfast");
            System.out.println("2. Lunch");
            System.out.println("3. Dinner");
            System.out.println("4. Snacks");
            mealTypeInput = scanner.nextLine();
        }

        MealTime mealTime = MealTime.NULL;
        MealType mealType = null;
        if (!mealTypeInput.equalsIgnoreCase("4")) {
            switch (mealTypeInput) {
                case "1":
                    mealTime = MealTime.BREAKFAST;
                    break;
                case "2":
                    mealTime = MealTime.LUNCH;
                    break;
                case "3":
                    mealTime = MealTime.DINNER;
                    break;
            }
        } else {
            mealType = MealType.SNACK;
        }


        // Depending on the search value, print out the right data
        if (mealTime == MealTime.NULL) {
            System.out.println("==== " + mealType + " ====");
        } else {
            System.out.println("==== " + mealTime + " ====");
        }
        int totalCalories = 0;
        for (Calories data : calorieTrackingData) {
            if (mealType == MealType.SNACK) {
                if (data.getSnackOrMeal() == MealType.SNACK) {
                    totalCalories += data.getCalories();
                    System.out.println("1. " + data.getFoodName());
                    System.out.println("Calories:" + data.getCalories() + " kcal");
                }
            } else {
                if (data.getMealTime() == mealTime) {
                    totalCalories += data.getCalories();
                    System.out.println("1. " + data.getFoodName());
                    System.out.println("Calories:" + data.getCalories() + " kcal");
                }
            }
        }
        System.out.println("Total calories: " + totalCalories + " kcal\n");
    }

    /**
     * Displays the comparison between the total calories logged for the day
     * and the user's calorie consumption goal. Indicates the surplus or deficit of calories.
     */
    private static void viewCaloriesConsumedVsGoal() {
        System.out.println("\n===================================");
        System.out.println("    CALORIES VS GOAL COMPARISON    ");
        System.out.println("===================================");
        // Retrieve the calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();

        // If empty, return to View MainFiles.Menu
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
                System.out.print("What is your goal for calories consumed today: ");
                calorieGoalInput = scanner.nextLine();
            }
        }

        // Find the total calories from the data
        int totalCalories = 0;
        for (Calories data : calorieTrackingData) {
            totalCalories += data.getCalories();
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
        System.out.println("\n===================================");
        System.out.println("         WORKOUT VOLUME DATA       ");
        System.out.println("===================================");
        // Retrieve the workout data
        ArrayList<Workout> workouts = user.getWorkoutData();

        // If no data, do nothing
        if (workouts.isEmpty()) {
            System.out.println("No workouts found.");
        } else {
            // Go through each exercise, do the calculation and then print it out
            for (Workout data : workouts) {
                for (Exercise exercise : data.getExercises()) {
                    System.out.println("Name of exercise: " + exercise.getExerciseName());
                    int setCount = 0;
                    float volume = 1;
                    for (Set set : exercise.getSets()) {
                        float weight = set.getWeightLifted();
                        volume *= set.getReps() * weight;
                        setCount++;
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
        System.out.println("\n===================================");
        System.out.println("   AVERAGE CALORIES PER MEAL TYPE  ");
        System.out.println("===================================");
        // Retrieve the calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();

        // If empty, return to View MainFiles.Menu
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
        for (Calories data : calorieTrackingData) {
            MealTime mealTime = data.getMealTime();
            MealType snackOrMeal = data.getSnackOrMeal();
            if (snackOrMeal == MealType.SNACK) {
                avgSnackCalories += data.getCalories();
                snacksCount++;
            } else {
                switch (mealTime) {
                    case BREAKFAST:
                        avgBreakfastCalories += data.getCalories();
                        breakfastCount++;
                        break;
                    case LUNCH:
                        avgLunchCalories += data.getCalories();
                        lunchCount++;
                        break;
                    case DINNER:
                        avgDinnerCalories += data.getCalories();
                        dinnerCount++;
                        break;
                }
            }
        }
        // Calculate the averages
        if (avgBreakfastCalories != 0) {
            avgBreakfastCalories /= breakfastCount;
        }
        if (avgLunchCalories != 0) {
            avgLunchCalories /= lunchCount;
        }
        if (avgDinnerCalories != 0) {
            avgDinnerCalories /= dinnerCount;
        }
        if (avgSnackCalories != 0) {
            avgSnackCalories /= snacksCount;
        }

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
        System.out.println("\n===================================");
        System.out.println("   TOTAL CALORIES: MEALS & SNACKS  ");
        System.out.println("===================================");
        // Retrieve calorie data
        ArrayList<Calories> calorieTrackingData = user.getCalorieData();

        // If empty, return to View MainFiles.Menu
        if (calorieTrackingData.isEmpty()) {
            System.out.println("No calories data found.");
            return;
        }

        // Find the total calories of both snacks and meals
        int snackCalories = 0;
        int mealCalories = 0;
        for (Calories data : calorieTrackingData) {
            MealType snackOrMeal = data.getSnackOrMeal();
            if (snackOrMeal.equals(MealType.SNACK)) {
                snackCalories += data.getCalories();
            } else {
                mealCalories += data.getCalories();
            }
        }

        // Calculate the percentage of each consumed
        float percentageSnack = ((float) snackCalories / (mealCalories + snackCalories)) * 100;
        float percentageMeal = ((float) mealCalories / (mealCalories + snackCalories)) * 100;

        // Output the calculated data
        System.out.println("🍟Total Snack Calories: " + snackCalories + " kcal");
        System.out.println("🍝 Total Meal Calories: " + mealCalories + " kcal");
        System.out.printf("Percentage Of Calories From Snacks: %.2f%%\n", percentageSnack);
        System.out.printf("Percentage Of Calories From Meals: %.2f%%\n", percentageMeal);
        System.out.println(" ");
    }

    /**
     * Calculates and displays the heaviest weight lifted for
     * each exercise in all recorded workouts.
     */
    private static void viewHeaviestLiftPerExercise() {
        System.out.println("\n===================================");
        System.out.println("    HEAVIEST LIFT PER EXERCISE     ");
        System.out.println("===================================");
        // Retrieve the data
        ArrayList<Workout> workouts = user.getWorkoutData();

        // Check if empty
        if (workouts.isEmpty()) {
            System.out.println("No workouts found.");
            return;
        }

        // Go through each exercise, and print out the max weight lifted for that exercise
        for (Workout data : workouts) {
            for (Exercise exercise : data.getExercises()) {
                System.out.println("Name: " + exercise.getExerciseName());
                float maxWeightLift = 0f;
                for (Set set : exercise.getSets()) {
                    float currentWeightLift = set.getWeightLifted();
                    if (maxWeightLift < currentWeightLift) {
                        maxWeightLift = currentWeightLift;
                    }
                }
                System.out.println("Max Weight Lifted : " + maxWeightLift + "kg");
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
        System.out.println("\n===================================");
        System.out.println("         PERFORMANCE SUMMARY       ");
        System.out.println("===================================");
        // Call all the functions needed to show the data
        System.out.println("=== Performance Summary ===");
        viewTodaysCalories();
        viewCaloriesConsumedVsGoal();
        System.out.println("\nWorkouts Performed: ");
        viewVolumeOfWorkout();
    }
}
