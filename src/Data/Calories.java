package Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
/**
 * This is a helper class to store all the calorie tracking data
 * @author Abdullah Al-Dhaibani
 */
public class Calories {

    public static LinkedHashMap<Object, Object> calorieTrackingData;
    // Removed this unused field.
    private String snackOrMeal;
    private String mealTime;
    private String foodName;
    private int calories;

    private static List<Calories> caloriesData = new ArrayList<>();

    public Calories(String snackOrMeal, String mealTime, String foodName,int calories) {
        this.snackOrMeal = snackOrMeal;
        this.mealTime = mealTime;
        this.foodName = foodName;
        this.calories = calories;
    }

public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, int calories) {
    validateEntry(snackOrMeal, mealType, foodName, calories);


    Calories calorieEntry = new Calories(snackOrMeal, mealType, foodName, calories);
    caloriesData.add(calorieEntry);
}

    private static void validateEntry(String snackOrMeal, String mealType, String foodName, int calories) {
    }

    public static ArrayList<HashMap<String, Object>> getData() {
    ArrayList<HashMap<String, Object>> calorieList = new ArrayList<>();
    for (Calories calorieEntry : caloriesData) {
        if (!isValidEntry(calorieEntry)) {
            logInvalidEntry(calorieEntry);
            continue;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("snackOrMeal", calorieEntry.getSnackOrMeal());
        map.put("mealTime", calorieEntry.getMealTime());
        map.put("foodName", calorieEntry.getFoodName());
        map.put("calories", calorieEntry.getCalories());
        calorieList.add(map);
    }
    return calorieList;
}

public String getSnackOrMeal() {
    return snackOrMeal;
}

    public void setSnackOrMeal(String snackOrMeal) {
        this.snackOrMeal = snackOrMeal;
    }

public String getMealTime() {
    return mealTime;
}

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

public String getFoodName() {
    return foodName;
}

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

public int getCalories() {
    return calories;
}

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String toString() {
        String safeSnackOrMeal = (snackOrMeal != null) ? snackOrMeal : "Unknown";
        String safeMealTime = (mealTime != null) ? mealTime : "N/A";
        String safeFoodName = (foodName != null) ? foodName : "Unnamed";
        if (safeSnackOrMeal.equalsIgnoreCase("snack")) {
            return safeSnackOrMeal + "," + safeFoodName + "," + calories;
        } else {
            return safeSnackOrMeal + "," + safeMealTime + "," + safeFoodName + "," + calories;
        }
    }

    private static boolean isValidEntry(Calories calorieEntry) {
    return calorieEntry != null &&
           calorieEntry.getSnackOrMeal() != null && !calorieEntry.getSnackOrMeal().trim().isEmpty() &&
           calorieEntry.getFoodName() != null && !calorieEntry.getFoodName().trim().isEmpty() &&
           calorieEntry.getCalories() >= 0;
}
private static void logInvalidEntry(Calories calorieEntry) {
    // Log invalid data (you can replace this with an actual logging framework)
    System.out.println("Invalid entry skipped: " + calorieEntry);
}


}
