import java.util.ArrayList;
import java.util.HashMap;

public class Calories {
    static ArrayList<HashMap<String, Object>> calorieTrackingData = new ArrayList<>();

    // Abdullah will work on this method
    public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, int calories) {
        HashMap<String, Object> mealEntry = new HashMap<>();
        mealEntry.put("mealType", mealType.toLowerCase());
        mealEntry.put("snackOrMeal", snackOrMeal.toLowerCase());
        mealEntry.put("name", foodName.toLowerCase());
        mealEntry.put("calories", calories);

        calorieTrackingData.add(mealEntry);
        System.out.println("Calories Stored!");
    }

    public static ArrayList<HashMap<String, Object>> getData() {
        return calorieTrackingData;
    }
}
