package Data;

import Enums.*;

import java.util.Objects;

/**
 * This is a helper class to store all the calorie tracking data
 * @author Abdullah Al-Dhaibani
 */
public class Calories {

    private MealType snackOrMeal;
    private MealTime mealTime;
    private String foodName;
    private int calories;

    /**
     * Constructs a Calories object with all relevant fields.
     *
     * @param snackOrMeal Specifies whether it's a snack or meal.
     * @param mealTime    Time of the meal (e.g., BREAKFAST, LUNCH).
     * @param foodName    Name of the food item.
     * @param calories    Number of calories for the food item.
     */
    public Calories(MealType snackOrMeal, MealTime mealTime, String foodName,int calories) {
        this.snackOrMeal = snackOrMeal;
        this.mealTime = mealTime;
        this.foodName = foodName;
        this.calories = calories;
    }

    public Calories(int i, String lunch) {
        this.snackOrMeal = MealType.SNACK;
        this.mealTime = MealTime.LUNCH;
        this.foodName = lunch;
        this.calories = i;
    }

    public MealType getSnackOrMeal() {
        return snackOrMeal;
    }

    public void setSnackOrMeal(MealType snackOrMeal) {
        this.snackOrMeal = snackOrMeal;
    }

    public MealTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(MealTime mealTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calories calories1 = (Calories) o;
        boolean equals = false;
        if (snackOrMeal.equals(calories1.getSnackOrMeal()) && mealTime.equals(calories1.getMealTime()) && foodName.equals(calories1.getFoodName()) && calories == calories1.getCalories()) {
            equals = true;
        }
        return equals;
    }

    /**
     * Returns a string representation of this Calories object.
     *
     * @return String formatted with relevant fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(snackOrMeal, mealTime, foodName, calories);
    }

    // last this is a to-string method
    public String toString() {
        if (snackOrMeal.getType() == MealType.SNACK.getType()) {
            return snackOrMeal  + "," + foodName + "," + calories;
        } else {
            return snackOrMeal + "," + mealTime + "," + foodName + "," + calories;
        }
    }

}
