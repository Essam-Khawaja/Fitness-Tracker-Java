import java.io.*;
import java.util.ArrayList;

public abstract class DataObject {
    protected void SaveData(ArrayList<Object> saveData){
        if (this.getClass() == Calories.class) {
            // Some code here
            File file = new File("CaloriesSave.txt");
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (Object o : saveData) {
                    bufferedWriter.write(o.toString() + '\n');
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (this.getClass() == Workout.class) {
            // Some code here
            File file = new File("WorkoutsSave.txt");
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (Object o : saveData) {
                    bufferedWriter.write(o.toString() + '\n');
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void LoadData(){
        if (this.getClass() == Calories.class) {
            // Some code here
        }
        if (this.getClass() == Workout.class) {
            // Some code here
        }
    }
}
