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

}