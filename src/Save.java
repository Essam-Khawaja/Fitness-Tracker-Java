import java.io.*;
import java.util.ArrayList;

public class Save {
    public static void SaveData(User user){
            // Some code here
            File file = new File("src/Save.csv");
            File file2 = new File("src/SaveTemp.csv");
            try {
                boolean saveCreated = file.createNewFile();     // Create new file if it is not there
                boolean tempCreated = file2.createNewFile();    // Create new file if it is not there
                // Write the contents of old save file to new file
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(file2);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String line = bufferedReader.readLine();
                boolean userExists = false;
                while (line != null) {
                    String[] data = line.split(",");
                    if (data[0].equals("U") && data[1].equals(user.getUsername())) {
                        bufferedWriter.write("U," + user.getUsername() + ',' + user.getEmail() + ',' + user.getPassword() + '\n');
                        // Copy new data onto new save file
                        for (Calories dataItem : user.getCalorieData()) {
                            bufferedWriter.write("C,");
                            bufferedWriter.write(dataItem.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        for (Workout dataItem : user.getWorkoutData()) {
                            bufferedWriter.write("W,");
                            bufferedWriter.write(dataItem.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        line = bufferedReader.readLine();
                        while (line != null) {
                            if (line.startsWith("U,")) {
                                break;
                            }
                            line = bufferedReader.readLine();
                        }
                        userExists = true;
                    } else {
                        bufferedWriter.write(line + "\n");
                        bufferedWriter.flush();
                        line = bufferedReader.readLine();
                    }
                }
                if (!userExists) {
                    bufferedWriter.write("U," + user.getUsername() + ',' + user.getEmail() + ',' + user.getPassword() + '\n');
                    // Copy new data onto new save file
                    for (Calories dataItem : user.getCalorieData()) {
                        bufferedWriter.write("C,");
                        bufferedWriter.write(dataItem.toString());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                    for (Workout dataItem : user.getWorkoutData()) {
                        bufferedWriter.write("W,");
                        bufferedWriter.write(dataItem.toString());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }

                // Replace all the content from old save file to new save file
                FileWriter fileWriter2 = new FileWriter(file);
                BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                FileReader fileReader2 = new FileReader(file2);
                BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
                String line2 = bufferedReader2.readLine();
                while (line2 != null) {
                    bufferedWriter2.write(line2 + "\n");
                    bufferedWriter2.flush();
                    line2 = bufferedReader2.readLine();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            file2.delete(); // Delete the temporary new save file
    }

    public static void LoadData(User user){
        File file = new File("src/Save.csv");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line.equals(null)) {
                System.out.println("No data found");
            } else {
                boolean userFound = false;
                while (line != null && !userFound) {
                    String[] data = line.split(",");
                    if (data[0].equals("U") && data[1].equals(user.getUsername())) {
                        userFound = true;
                        user.resetTrackingData();
                        line = bufferedReader.readLine();
                        data = line.split(",");
                        while (line != null && !line.startsWith("U,")) {
                            if (data[0].equals("C")) {
                                String snackOrMeal = data[1];
                                if (snackOrMeal.equalsIgnoreCase("Snack")) {
                                    String foodName = data[2];
                                    int calories = Integer.parseInt(data[3]);
                                    Calories thisData = new Calories(snackOrMeal, "", foodName, calories);
                                    user.addCalorieData(thisData);
                                } else {
                                    String mealTime = data[2];
                                    String foodName = data[3];
                                    int calories = Integer.parseInt(data[4]);
                                    Calories thisData = new Calories(snackOrMeal, mealTime, foodName, calories);
                                    user.addCalorieData(thisData);
                                }
                            } else if (data[0].equals("W")) {
                                String workoutPlan = data[1];
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
                            line = bufferedReader.readLine();
                            if (line != null) {
                                data = line.split(",");
                            }
                        }
                    } else {
                        line = bufferedReader.readLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
