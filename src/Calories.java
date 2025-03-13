import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a helper class to store all the calorie tracking data
 * @author Abdullah Al-Dhaibani
 */
public class Calories extends DataObject {
    static ArrayList<HashMap<String, Object>> calorieTrackingData = new ArrayList<>();

    /**
     * Takes different variables and stores them into a hashmap, and then adds the hashmap to the data ArrayList.
     * @param snackOrMeal -> String that stores whether it is a snack or meal
     * @param mealType -> String that stores the type of meal it is (Breakfast/Lunch/Dinner)
     * @param foodName -> String that stores the name of the food
     * @param calories -> Integer that stores the calories of the food
     */
    public static void storeCaloriesDataEntry(String snackOrMeal, String mealType, String foodName, int calories) {
        HashMap<String, Object> mealEntry = new HashMap<>();
        mealEntry.put("mealType", mealType.toLowerCase());
        mealEntry.put("snackOrMeal", snackOrMeal.toLowerCase());
        mealEntry.put("name", foodName.toLowerCase());
        mealEntry.put("calories", calories);

        calorieTrackingData.add(mealEntry);
        System.out.println("Calories Stored!");
    }

    /**
     * Meant to access the calorie tracking data
     * @return -> An ArrayList<HashMap<String, Object>> of all the calorie data
     */
    public static ArrayList<HashMap<String, Object>> getData() {
        return calorieTrackingData;
    }
}
