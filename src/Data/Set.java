package Data;

import java.util.Objects;

public class Set {
    private int reps;
    private float weightLifted;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return reps == set.reps && weightLifted == set.weightLifted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reps, weightLifted);
    }

    //To-String
    public String toString() {
        return "S,"+ reps + "," + weightLifted;
    }
}
