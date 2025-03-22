package Data;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return exerciseName.equals(exercise.exerciseName) && sets.equals(exercise.sets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseName, sets);
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
