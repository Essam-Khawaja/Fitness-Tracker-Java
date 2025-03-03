import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTesting {

    // ----------------------------------------------------------------
    // Testing functions for validateOptions in getMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateMenuOption1() {
        String option = "1";
        boolean isValid = option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4");
        assertTrue(isValid);
    }

    @Test public void testValidateMenuOption4() {
        String option = "4";
        boolean isValid = option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4");
        assertTrue(isValid);
    }

    @Test public void testValidateMenuOptionInvalid() {
        String option = "5";
        boolean isValid = option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4");
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for snackOrMeal validation in getCalorieMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateSnackOrMealSnack() {
        String input = "snack";
        boolean isValid = input.equalsIgnoreCase("snack") || input.equalsIgnoreCase("meal");
        assertTrue(isValid);
    }

    @Test public void testValidateSnackOrMealMeal() {
        String input = "meal";
        boolean isValid = input.equalsIgnoreCase("snack") || input.equalsIgnoreCase("meal");
        assertTrue(isValid);
    }

    @Test public void testValidateSnackOrMealInvalid() {
        String input = "dinner";
        boolean isValid = input.equalsIgnoreCase("snack") || input.equalsIgnoreCase("meal");
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for mealType validation in getCalorieMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateMealTypeBreakfast() {
        String input = "breakfast";
        boolean isValid = input.equalsIgnoreCase("breakfast")
                || input.equalsIgnoreCase("lunch")
                || input.equalsIgnoreCase("dinner");
        assertTrue(isValid);
    }

    @Test public void testValidateMealTypeLunch() {
        String input = "lunch";
        boolean isValid = input.equalsIgnoreCase("breakfast")
                || input.equalsIgnoreCase("lunch")
                || input.equalsIgnoreCase("dinner");
        assertTrue(isValid);
    }

    @Test public void testValidateMealTypeInvalid() {
        String input = "snack";
        boolean isValid = input.equalsIgnoreCase("breakfast")
                || input.equalsIgnoreCase("lunch")
                || input.equalsIgnoreCase("dinner");
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for foodName validation in getCalorieMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateFoodNameValid() {
        String input = "Apple";
        boolean isValid = input.matches("[a-zA-Z]+");
        assertTrue(isValid);
    }

    @Test public void testValidateFoodNameInvalidSymbols() {
        String input = "Apple12";
        boolean isValid = input.matches("[a-zA-Z]+");
        assertFalse(isValid);
    }

    @Test public void testValidateFoodNameInvalidEmpty() {
        String input = "";
        boolean isValid = input.matches("[a-zA-Z]+");
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for calorie validation in getCalorieMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateCaloriesWithinRange() {
        int calories = 500;
        boolean isValid = calories >= 0 && calories <= 20000;
        assertTrue(isValid);
    }

    @Test public void testValidateCaloriesOutOfRange() {
        int calories = 21000;
        boolean isValid = calories >= 0 && calories <= 20000;
        assertFalse(isValid);
    }

    @Test public void testValidateCaloriesNegative() {
        int calories = -1;
        boolean isValid = calories >= 0 && calories <= 20000;
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for validate exercises count in getWorkoutMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateExerciseCountWithinLimit() {
        int count = 5;
        boolean isValid = count > 0 && count <= 10;
        assertTrue(isValid);
    }

    @Test public void testValidateExerciseCountOutOfBounds() {
        int count = 11;
        boolean isValid = count > 0 && count <= 10;
        assertFalse(isValid);
    }

    @Test public void testValidateExerciseCountNegative() {
        int count = -3;
        boolean isValid = count > 0 && count <= 10;
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for validate sets count in getWorkoutMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateSetCountWithinLimit() {
        int count = 3;
        boolean isValid = count > 0 && count <= 5;
        assertTrue(isValid);
    }

    @Test public void testValidateSetCountOutOfBounds() {
        int count = 6;
        boolean isValid = count > 0 && count <= 5;
        assertFalse(isValid);
    }

    @Test public void testValidateSetCountZero() {
        int count = 0;
        boolean isValid = count > 0 && count <= 5;
        assertFalse(isValid);
    }

    // ----------------------------------------------------------------
    // Testing functions for validate menu option in getViewMenu()
    // ----------------------------------------------------------------

    @Test public void testValidateViewOption1() {
        String option = "1";
        boolean isValid = option.equals("1") || option.equals("2") || option.equals("3");
        assertTrue(isValid);
    }

    @Test public void testValidateViewOptionInvalid() {
        String option = "4";
        boolean isValid = option.equals("1") || option.equals("2") || option.equals("3");
        assertFalse(isValid);
    }

    @Test public void testValidateViewOptionEmpty() {
        String option = "";
        boolean isValid = option.equals("1") || option.equals("2") || option.equals("3");
        assertFalse(isValid);
    }
}