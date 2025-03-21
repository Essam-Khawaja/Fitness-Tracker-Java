package Enums;

public enum MealType {
    SNACK('S'), MEAL('M');

    private final char type;

    MealType(char type) {
        this.type = type;
    }

    public char getType() {
        return this.type;
    }

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
