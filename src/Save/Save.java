package Save;

import Data.*;
import Enums.MealTime;
import Enums.MealType;
import Enums.WorkoutPlan;

import java.io.*;
import java.util.ArrayList;

/**
 * This is a helper class meant to help the program save and load data
 * Major methods include:
 * - SaveData -> Saves all the user data
 * - LoadData -> Loads all the user data
 * - SaveNewUser -> Saves a new user upon a sign in
 * - CheckUserExists -> Checks if the user is present upon login
 * @author Syed Essam Uddin Khawaja
 */
public class Save {
    /**
     * Saves all the calorie and workout data and the corresponding user data into the file
     * The format is as follows:
     * U, USERNAME, PASSWORD, EMAIL
     * C, SNACK/MEAL, MEAL TIME, FOOD NAME, CALORIES
     * W, WORKOUT PLAN, E, EXERCISE NAME, S, REPS, WEIGHT LIFTED
     * @param user -> The user of which to save the data for
     */
    public static void SaveData(User user){
        if (user.getCalorieData().isEmpty() && user.getWorkoutData().isEmpty()){
            System.out.println("Nothing to Save.");
            return;
        }
            File file = new File("src/Save/Save.csv");  // Open the save file
            File file2 = new File("src/Save/SaveTemp.csv"); // Create a new temporary save file
            try {
                boolean saveCreated = file.createNewFile();     // Create new file if it is not there
                boolean tempCreated = file2.createNewFile();    // Create new file if it is not there
                // Write the contents of old save file to new file
                FileReader fileReader = new FileReader(file);   // Open the old save file for reading
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(file2);  // Open the temporary save file for writing
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String line = bufferedReader.readLine();
                boolean userExists = false;     // Variable to store whether the user is present or not
                while (line != null) {
                    String[] data = line.split(",");    // Split the line based on comma
                    if (data[0].equals("U") && data[3].equals(user.getEmail())) {    // If the line corresponds to the current user
                        bufferedWriter.write("U," + user.toString() + '\n');    // Write the user data
                        // Copy new data onto new save file
                        for (Calories dataItem : user.getCalorieData()) {   // Write the calorie data
                            bufferedWriter.write("C,");
                            bufferedWriter.write(dataItem.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        for (Workout dataItem : user.getWorkoutData()) {    // Write the workout data
                            bufferedWriter.write("W,");
                            bufferedWriter.write(dataItem.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        line = bufferedReader.readLine();   // Read the line
                        while (line != null) {      // Skip all the lines until you find the next user
                            if (line.startsWith("U,")) {
                                break;
                            }
                            line = bufferedReader.readLine();
                        }
                        userExists = true;      // Already found user, so we can set it to true
                    } else {
                        // Otherwise, just write the previous save file content into new temporary save file
                        bufferedWriter.write(line + "\n");
                        bufferedWriter.flush();
                        line = bufferedReader.readLine();
                    }
                }
                // If we did not find the user, then add it to the end of the file
                if (!userExists) {
                    bufferedWriter.write("U," + user.toString() + '\n');    // Add User data
                    for (Calories dataItem : user.getCalorieData()) {       // Add the calorie data
                        bufferedWriter.write("C,");
                        bufferedWriter.write(dataItem.toString());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                    for (Workout dataItem : user.getWorkoutData()) {    // Add the workout data
                        bufferedWriter.write("W,");
                        bufferedWriter.write(dataItem.toString());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
                // Close all the previous file objects
                fileReader.close();
                bufferedReader.close();
                fileWriter.close();
                bufferedWriter.close();

                // Replace all the content from old save file to new save file
                FileWriter fileWriter2 = new FileWriter(file);      // Open save file for writing
                BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                FileReader fileReader2 = new FileReader(file2);     // Open temporary save file for reading
                BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
                String line2 = bufferedReader2.readLine();
                // Copy all the data from the temporary save file to the original save file
                while (line2 != null) {
                    bufferedWriter2.write(line2 + "\n");
                    bufferedWriter2.flush();
                    line2 = bufferedReader2.readLine();
                }
                // Close all the file objects
                fileWriter2.close();
                bufferedWriter2.close();
                fileReader2.close();
                bufferedReader2.close();

                file2.delete(); // Delete the temporary new save file
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * Goes through the save file and finds the corresponding user and copies all the data into the user object
     * @param user -> The user in which we have to load the data
     * @param path -> The path to the file
     */
    public static void LoadData(User user, String path){
        File file = new File(path);  // Open the file
        try {
            FileReader fileReader = new FileReader(file);       // Open the file for reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line.equals(null)) {
                System.out.println("No data found");    // If save file is empty, say no data found
            } else {
                boolean userFound = false;      // This checks whether we have found the user data
                while (line != null && !userFound) {
                    String[] data = line.split(",");    // Split the data based on commas
                    if (data[0].equals("U") && data[3].equals(user.getEmail())) {    // If this is the corresponding user
                        userFound = true;
                        user.resetTrackingData();   // Reset all the data
                        line = bufferedReader.readLine();
                        data = line.split(",");     // Get the data for the next line
                        while (line != null && !line.startsWith("U,")) {    // Go through all the lines until you find next user
                            // Parse the calorie data and store it into user
                            if (data[0].equals("C")) {
                                MealType snackOrMeal = MealType.getTypeEnum(data[1]);
                                if (snackOrMeal.getType() == MealType.SNACK.getType()) {
                                    String foodName = data[2];
                                    int calories = Integer.parseInt(data[3]);
                                    Calories thisData = new Calories(snackOrMeal, MealTime.NULL, foodName, calories);
                                    user.addCalorieData(thisData);
                                } else {
                                    MealTime mealTime = MealTime.getTimeEnum(data[2]);
                                    String foodName = data[3];
                                    int calories = Integer.parseInt(data[4]);
                                    Calories thisData = new Calories(snackOrMeal, mealTime, foodName, calories);
                                    user.addCalorieData(thisData);
                                }
                            } // Parse the workout data and store it into user
                            else if (data[0].equals("W")) {
                                WorkoutPlan workoutPlan = WorkoutPlan.getPlanEnum(data[1]);
                                ArrayList<Exercise> exercises = new ArrayList<>();
                                ArrayList<Set> sets = new ArrayList<>();
                                for (int i = 2; i < data.length; i++) {
                                    if (data[i].equals("E")) {
                                        int j = i + 1;
                                        String exerciseName = data[j];
                                        sets.clear();
                                        while (!(data[j].equals("E")) && (j < data.length - 1)) {
                                            if (data[j].equals("S")) {
                                                int reps = Integer.parseInt(data[j + 1]);
                                                float weightLifted = Float.parseFloat(data[j + 2]);
                                                Set thisSet = new Set(reps, weightLifted);
                                                sets.add(thisSet);
                                            }
                                            j++;
                                        }
                                        Exercise exercise = new Exercise(exerciseName, sets);
                                        exercises.add(exercise);
                                    }
                                }
                                Workout thisWorkout = new Workout(workoutPlan, exercises);
                                user.addWorkoutData(thisWorkout);
                            }
                            line = bufferedReader.readLine();   // Go next line
                            if (line != null) {
                                data = line.split(",");     // If not end of file, then split it
                            }
                        }
                    } else {
                        line = bufferedReader.readLine();   // If user not found, then continue reading file
                    }
                }
                if (!userFound) {   // If we have not found the user, output that no data found
                    System.out.println("No data found");
                }
            }
            // Close the corresponding file objects
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method saves a new user upon sign up
     * @param user -> The new user to be saved
     */
    public static void SaveNewUser(User user){
        File file = new File("src/Save/UserSave.csv");      // Opens the user save file
        File file2 = new File("src/Save/UserTempSave.csv");     // Creates a temporary save user file
        try {
            boolean saveCreated = file.createNewFile();     // Create a new file if not present
            boolean tempCreated = file2.createNewFile();    // Create a new file if not present
            FileReader fileReader = new FileReader(file);   // Open the old user save file for reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(file2);  // Open the temporary save file for writing
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = bufferedReader.readLine();
            // Copy all the content of the old user save file into the temporary one
            while (line != null) {
                bufferedWriter.write(line + "\n");
                bufferedWriter.flush();
                line = bufferedReader.readLine();
            }
            // Write the new user data
            bufferedWriter.write(user.toString() + "\n");
            bufferedWriter.flush();
            // Close the corresponding file objects
            bufferedWriter.close();
            bufferedReader.close();
            fileWriter.close();
            fileReader.close();

            // Re-write the temporary save file contents to old save file
            FileWriter fileWriter2 = new FileWriter(file);  // Open the old save file for writing
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            FileReader fileReader2 = new FileReader(file2); // Open the  temporary save file for reading
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

            // Copy all the contents of the temporary file into save file
            String line2 = bufferedReader2.readLine();
            while (line2 != null) {
                bufferedWriter2.write(line2 + "\n");
                bufferedWriter2.flush();
                line2 = bufferedReader2.readLine();
            }
            // Close the corresponding file objects
            fileWriter2.close();
            bufferedReader2.close();
            fileReader2.close();
            bufferedWriter2.close();

            file2.delete();     // Delete the temporary file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method checks if a user already exists with that email
     * @param user -> The user object from the menu
     * @return -> An integer with 3 values: -1: Does not exist, 0: Email correct, not password, 1: Both email and password correct
     */
    public static int ValidateUser(User user){
        File file = new File("src/Save/UserSave.csv");  // Open the user save file
        int exists = -1;
        try {
            FileReader fileReader = new FileReader(file);   // Open the save for reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null && exists == -1) {
                String[] data = line.split(",");    // Split the line by commas
                if (data[2].equals(user.getEmail())) {  // Compare emails
                    exists = 0;
                    if (data[1].equals(user.getPassword())) {
                        exists = 1;
                        user.setUsername(data[0]);
                        break;
                    }
                }
                line = bufferedReader.readLine();
            }
            // CLose the corresponding file objects
            fileReader.close();
            bufferedReader.close();

            return exists;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
