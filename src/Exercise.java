import java.util.ArrayList;

public class Exercise {
    private String exerciseName;
    private ArrayList<Set> sets;

    public Exercise(String exerciseName, ArrayList<Set> sets) {
        this.exerciseName = exerciseName;
        this.sets = sets;
    }

    //Getters and Setters
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

//To-String
    public String toString() {
        String objectString = "E,";
        objectString += exerciseName + ",";
        int count = 0;
        for (Set set : sets) {
            objectString += set.toString();
            count++;
            if (count != sets.size()) {
                objectString += ",";
            }
        }
        return objectString;
    }
}
