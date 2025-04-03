package Enums;

/**
 * This enum holds the type of food to be stored
 * @author Syed Essam Uddin Khawaja
 */
public enum MealType {
    // All possible values
    SNACK('S'), MEAL('M');

    // The type of food
    private final char type;

    /**
     * Constructor for the enum
     * @param type -> the type of meal
     */
    MealType(char type) {
        this.type = type;
    }

    /**
     * @return -> the type of the instance
     */
    public char getType() {
        return this.type;
    }

    /**
     * Checks and returns the right meal type
     * @param type -> a String to check for
     * @return -> returns the right meal type based on the string
     */
    public static MealType getTypeEnum(String type) {
        switch (type) {
            case "SNACK":
                return MealType.SNACK;
            case "MEAL":
                return MealType.MEAL;
        }
        return null;
    }
}
