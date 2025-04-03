package Enums;

/**
 * This enum holds the time of the meal
 * @author Syed Essam Uddin Khawaja
 */
public enum MealTime {
    // All the possible types
    BREAKFAST('B'), LUNCH('L'), DINNER('D'), NULL('N');

    // This stores the time of the meal
    private char time;

    /**
     * The constructor for this enum
     * @param time -> the time of the meal
     */
    MealTime(char time) {
        this.time = time;
    }

    /**
     * Returns the meal time of the instance
     * @return -> the time of the meal
     */
    public char getMealTime() {
        return time;
    }

    /**
     * Checks and returns the correct meal time
     * @param mealTime -> a string to check
     * @return -> the correct meal time based on string parameter
     */
    public static MealTime getTimeEnum(String mealTime) {
        switch (mealTime) {
            case "BREAKFAST":
                return MealTime.BREAKFAST;
            case "LUNCH":
                return MealTime.LUNCH;
            case "DINNER":
                return MealTime.DINNER;
        }
        return MealTime.NULL;
    }
}
