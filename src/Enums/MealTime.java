package Enums;

public enum MealTime {
    BREAKFAST('B'), LUNCH('L'), DINNER('D');

    private char time;

    MealTime(char time) {
        this.time = time;
    }

    public char getMealTime() {
        return time;
    }

    public static MealTime getTimeEnum(String mealTime) {
        switch (mealTime) {
            case "BREAKFAST":
                return MealTime.BREAKFAST;
            case "LUNCH":
                return MealTime.LUNCH;
            case "DINNER":
                return MealTime.DINNER;
        }
        return null;
    }
}
