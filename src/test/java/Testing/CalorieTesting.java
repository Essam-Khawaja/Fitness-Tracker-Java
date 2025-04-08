package Testing;

import Data.Calories;
import Enums.MealType;
import Enums.MealTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CalorieTesting {

    @Test
    public void testCaloriesObjectCreation() {
        // Create a meal-type Calories object
        Calories entry = new Calories(MealType.MEAL, MealTime.LUNCH, "pasta", 600);

        // Validate fields
        assertEquals(MealType.MEAL, entry.getSnackOrMeal());
        assertEquals(MealTime.LUNCH, entry.getMealTime());
        assertEquals("pasta", entry.getFoodName());
        assertEquals(600, entry.getCalories());
    }

    @Test
    public void testSnackEntryToString() {
        // Create a snack-type entry
        Calories entry = new Calories(MealType.SNACK, MealTime.NULL, "apple", 95);

        // Validate toString output (based on logic in class)
        String expected = "SNACK,apple,95";
        assertEquals(expected, entry.toString());
    }

    @Test
    public void testMealEntryToString() {
        // Create a breakfast meal entry
        Calories entry = new Calories(MealType.MEAL, MealTime.BREAKFAST, "eggs", 150);

        // Validate toString output
        String expected = "MEAL,BREAKFAST,eggs,150";
        assertEquals(expected, entry.toString());
    }

    @Test
    public void testCaloriesEquality() {
        // Two identical entries
        Calories entry1 = new Calories(MealType.MEAL, MealTime.LUNCH, "chicken", 500);
        Calories entry2 = new Calories(MealType.MEAL, MealTime.LUNCH, "chicken", 500);

        // Should be equal
        assertEquals(entry1, entry2);
        assertEquals(entry1.hashCode(), entry2.hashCode());
    }

    @Test
    public void testCaloriesInequality() {
        // Different calorie values
        Calories entry1 = new Calories(MealType.MEAL, MealTime.LUNCH, "chicken", 500);
        Calories entry2 = new Calories(MealType.MEAL, MealTime.LUNCH, "chicken", 600);

        // Should not be equal
        assertNotEquals(entry1, entry2);
    }
}