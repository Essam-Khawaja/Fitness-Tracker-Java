import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a helper class to store all the calorie tracking data
 * @author Abdullah Al-Dhaibani
 */
public class Calories {

    private String snackOrMeal;
    private String mealTime;
    private String foodName;
    private int calories;


    public Calories(String snackOrMeal, String mealTime, String foodName,int calories) {
        this.snackOrMeal = snackOrMeal;
        this.mealTime = mealTime;
        this.foodName = foodName;
        this.calories = calories;
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

    // last this is a to-string method
    public String toString() {
        if (snackOrMeal.equalsIgnoreCase("snack")) {
            return snackOrMeal  + "," + foodName + "," + calories;
        } else {
            return snackOrMeal + "," + mealTime + "," + foodName + "," + calories;
        }
    }

}
