package Data;

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

    //To-String
    public String toString() {
        return "S,"+ reps + "," + weightLifted;
    }
}
