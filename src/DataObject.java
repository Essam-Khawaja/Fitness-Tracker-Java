import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataObject {
    public static void SaveData(ArrayList<HashMap<String, Object>> saveCaloriesData, ArrayList<HashMap<String, Object>> saveWorkoutData){
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
                while (line != null) {
                    bufferedWriter.write(line + "\n");
                    bufferedWriter.flush();
                    line = bufferedReader.readLine();
                }
                // Copy new data onto new save file
                for (HashMap<String, Object> dataItem : saveCaloriesData) {
                    bufferedWriter.write("C,");
                    for (Object key : dataItem.keySet()) {
                        bufferedWriter.write(String.valueOf(dataItem.get(key)) + ",");
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                for (HashMap<String, Object> dataItem : saveWorkoutData) {
                    bufferedWriter.write("W,");
                    for (Object key : dataItem.keySet()) {
                        bufferedWriter.write(String.valueOf(dataItem.get(key)) + ",");
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
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

    public static void LoadData(){
        File file = new File("src/Save.csv");
        try {
            if (!file.exists()) {
                System.out.println("File does not exist");
            } else {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                Calories.calorieTrackingData.clear();
                Workout.workouts.clear();

                String line = bufferedReader.readLine();
                while (line != null) {
                    String[] data = line.split(",");
                    if (data[0].equals("C")) {
                        String snackOrMeal = data[1];
                        String mealTyoe = data[2];
                        String mealName = data[3];
                        int mealCalories = Integer.parseInt(data[4]);
                        Calories.storeCaloriesDataEntry(snackOrMeal, mealTyoe, mealName, mealCalories);
                    } else if (data[0].equals("W")) {
                        System.out.println(data[1]);
                        System.out.println(data[2]);
                    }
                    line = bufferedReader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
