//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class CalorieTesting {
//
//    @Test
//    public void testStoreCaloriesDataEntry() {
//        // Clear existing data to avoid test interference
//        Calories.calorieTrackingData.clear();
//
//        // Store a new meal entry with details
//        Calories.storeCaloriesDataEntry("meal", "lunch", "pasta", 600);
//
//        // Retrieve the stored data
//        ArrayList<HashMap<String, Object>> data = Calories.getData();
//
//        // Validate that one entry has been stored
//        assertEquals(1, data.size());
//
//        // Ensure data is stored correctly
//        assertEquals(1, data.size());
//        assertEquals("lunch", data.get(0).get("mealType"));
//        assertEquals("meal", data.get(0).get("snackOrMeal"));
//        assertEquals("pasta", data.get(0).get("name"));
//        assertEquals(600, data.get(0).get("calories"));
//    }
//
//    @Test
//    public void testGetDataReturnsEmptyInitially() {
//        // Clear any existing data before testing
//        Calories.calorieTrackingData.clear();
//
//        // Ensure that the calorie tracking list is empty at the start
//        assertTrue(Calories.getData().isEmpty());
//    }
//
//    @Test
//    public void testGetDataAfterAddingMultipleEntries() {
//        // Clear existing data to prevent interference from other tests
//        Calories.calorieTrackingData.clear();
//
//        // Add multiple food entries (one snack and one meal)
//        Calories.storeCaloriesDataEntry("snack", "", "apple", 95);
//        Calories.storeCaloriesDataEntry("meal", "breakfast", "eggs", 150);
//
//        // Retrieve stored data
//        ArrayList<HashMap<String, Object>> data = Calories.getData();
//
//        // Verify that two entries were added
//        assertEquals(2, data.size());
//
//        // Validate the first entry (snack)
//        assertEquals("snack", data.get(0).get("snackOrMeal"));
//        assertEquals("apple", data.get(0).get("name"));
//        assertEquals(95, data.get(0).get("calories"));
//
//        // Validate the second entry (meal - breakfast)
//        assertEquals("meal", data.get(1).get("snackOrMeal"));
//        assertEquals("breakfast", data.get(1).get("mealType"));
//        assertEquals("eggs", data.get(1).get("name"));
//        assertEquals(150, data.get(1).get("calories"));
//    }
//}