package Data;

import java.util.Objects;

/**
 * This is a helper class to store the Set data
 * @author Abdullah Al-Dhaibani
 */
public class Set {
    private int reps;
    private float weightLifted;

    /**
     * Constructs a new Set instance.
     *
     * @param reps         Number of repetitions performed in the set.
     * @param weightLifted The amount of weight lifted (in kg).
     */
    public Set(int reps, float weightLifted) {
        this.reps = reps;
        this.weightLifted = weightLifted;
    }

    //Getters and Setters
    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getWeightLifted() {
        return weightLifted;
    }

    public void setWeightLifted(float weightLifted) {
        this.weightLifted = weightLifted;
    }

    /**
     * Compares this Set object with another for equality.
     *
     * @param o The object to compare.
     * @return true if reps and weightLifted are equal; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return reps == set.reps && weightLifted == set.weightLifted;
    }

    /**
     * Returns the hash code for this set based on its fields.
     *
     * @return Hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(reps, weightLifted);
    }

    /**
     * Returns a comma-separated string representation of the set.
     * Format: S,reps,weight
     *
     * @return Formatted string of the set.
     */
    //To-String
    public String toString() {
        return "S,"+ reps + "," + weightLifted;
    }
}
