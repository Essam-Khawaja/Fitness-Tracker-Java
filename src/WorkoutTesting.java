import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class WorkoutTesting {
    @Test public void testValidateWorkoutPlanPush() {
        boolean expected = true;
        boolean actual = Workout.validateWorkoutPlan("push");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWorkoutPlanUpperAllLower() {
        boolean expected = true;
        boolean actual = Workout.validateWorkoutPlan("upper");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWorkoutPlanLegAllUpper() {
        boolean expected = true;
        boolean actual = Workout.validateWorkoutPlan("LEG");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWorkoutPlanOutOfBounds() {
        boolean expected = false;
        boolean actual = Workout.validateWorkoutPlan("invalidPlan");
        assertEquals(expected, actual);
    }
}
