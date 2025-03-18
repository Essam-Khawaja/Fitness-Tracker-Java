import java.util.ArrayList;

public class Exercise {
    private String exerciseName;
    private ArrayList<Set> sets;

    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
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
        String objectString = "";
        objectString += exerciseName + ",";
        for (Set set : sets) {
            objectString += set.toString() + ",";
        }
        return objectString;
    }
}
