package Enums;

/**
 * This enum stores the workout plan
 * @author Syed Essam Uddin Khawaja
 */
public enum WorkoutPlan {
    // All possible values
    PUSH(0), PULL(1), LEGS(2), UPPER(3), LOWER(4), FULL_BODY(5);

    // The plan
    private int plan;

    /**
     * Constructor for the enum
     * @param plan -> Integer which refers to the type of plan
     */
    WorkoutPlan(int plan) {
        this.plan = plan;
    }

    /**
     * @return -> the plan of the instance
     */
    public int getPlan() {
        return plan;
    }

    /**
     * Takes a string and finds the corresponding plan
     * @param plan -> String that refers to some workout plan
     * @return -> returns the correct corresponding plan
     */
    public static WorkoutPlan getPlanEnum(String plan) {
        switch (plan) {
            case "PUSH":
                return WorkoutPlan.PUSH;
            case "PULL":
                return WorkoutPlan.PULL;
            case "LEGS":
                return WorkoutPlan.LEGS;
            case "UPPER":
                return WorkoutPlan.UPPER;
            case "LOWER":
                return WorkoutPlan.LOWER;
            case "FULL_BODY":
                return WorkoutPlan.FULL_BODY;
        }
        return null;
    }
}
