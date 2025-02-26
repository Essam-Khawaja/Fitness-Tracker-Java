import java.util.ArrayList;
import java.util.HashMap;

public class Calories {
    public static ArrayList<HashMap<String, Object>> calorieTrackingData = new ArrayList<>();

    // Abdullah will work on this method
    public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, int calories) {
        HashMap<String, Object> mealEntry = new HashMap<>();
        mealEntry.put("mealType", mealType);
        mealEntry.put("snackOrMeal", snackOrMeal);
        mealEntry.put("name", foodName);
        mealEntry.put("calories", calories);

        calorieTrackingData.add(mealEntry);
        System.out.println("Calories Stored!");
    }
}
