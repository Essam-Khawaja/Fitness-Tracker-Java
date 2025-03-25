package Data;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is a helper class to store all Exercise data
 * @author Abdullah Al-Dhaibani
 */

public class Exercise {
    private String exerciseName;
    private ArrayList<Set> sets;

    /**
     * Constructs a new Exercise instance.
     *
     * @param exerciseName The name of the exercise (e.g., "Bench Press").
     * @param sets         A list of Set objects representing repetitions and weight.
     */
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

    /**
     * Returns a comma-separated string representation of the Exercise.
     * Format: E,exerciseName,set1,set2,...
     *
     * @return Formatted string of the exercise.
     */
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
