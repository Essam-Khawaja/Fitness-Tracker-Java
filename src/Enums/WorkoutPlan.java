package Enums;

public enum WorkoutPlan {
    PUSH(0), PULL(1), LEGS(2), UPPER(3), LOWER(4), FULL_BODY(5);

    private int plan;
    WorkoutPlan(int plan) {
        this.plan = plan;
    }

    public int getPlan() {
        return plan;
    }

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
