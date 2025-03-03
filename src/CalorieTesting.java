import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CalorieTesting {

    @Test
    public void testStoreCaloriesDataEntry() {
        // Clear existing data to avoid test interference
        Calories.calorieTrackingData.clear();

        // Store a new entry
        Calories.storeCaloriesDataEntry("meal", "lunch", "pasta", 600);

        // Retrieve data
        ArrayList<HashMap<String, Object>> data = Calories.getData();

        // Ensure data is stored correctly
        assertEquals(1, data.size());
        assertEquals("lunch", data.get(0).get("mealType"));
        assertEquals("meal", data.get(0).get("snackOrMeal"));
        assertEquals("pasta", data.get(0).get("name"));
        assertEquals(600, data.get(0).get("calories"));
    }

    @Test
    public void testGetDataReturnsEmptyInitially() {
        // Clear data before testing
        Calories.calorieTrackingData.clear();

        // Ensure data starts empty
        assertTrue(Calories.getData().isEmpty());
    }

    @Test
    public void testGetDataAfterAddingMultipleEntries() {
        // Clear data
        Calories.calorieTrackingData.clear();

        // Add multiple entries
        Calories.storeCaloriesDataEntry("snack", "", "apple", 95);
        Calories.storeCaloriesDataEntry("meal", "breakfast", "eggs", 150);

        // Retrieve data
        ArrayList<HashMap<String, Object>> data = Calories.getData();

        // Ensure the correct number of entries
        assertEquals(2, data.size());

        // Validate first entry
        assertEquals("snack", data.get(0).get("snackOrMeal"));
        assertEquals("apple", data.get(0).get("name"));
        assertEquals(95, data.get(0).get("calories"));

        // Validate second entry
        assertEquals("meal", data.get(1).get("snackOrMeal"));
        assertEquals("breakfast", data.get(1).get("mealType"));
        assertEquals("eggs", data.get(1).get("name"));
        assertEquals(150, data.get(1).get("calories"));
    }
}