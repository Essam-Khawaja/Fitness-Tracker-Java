import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTesting {
    // Testing functions for validateWorkoutPlan()
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

    // ----------------------------------------------------------------

    // Testing functions for validateWeightLifted
    @Test public void testValidateWeightLiftedSmallNumber() {
        boolean expected = true;
        boolean actual = Workout.validateWeightLifted("1");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedLargeNumber() {
        boolean expected = true;
        boolean actual = Workout.validateWeightLifted("1000");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedZero() {
        boolean expected = false;
        boolean actual = Workout.validateWeightLifted("0");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedOutOfBounds() {
        boolean expected = false;
        boolean actual = Workout.validateWeightLifted("100000000000");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedNegativeNumber() {
        boolean expected = false;
        boolean actual = Workout.validateWeightLifted("-1");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedDecimal() {
        boolean expected = true;
        boolean actual = Workout.validateWeightLifted("25.5");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedInvalidString() {
        boolean expected = false;
        boolean actual = Workout.validateWeightLifted("invalidString");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWeightLiftedEmptyString() {
        boolean expected = false;
        boolean actual = Workout.validateWeightLifted("");
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------

    // Testing functions for validateReps
    @Test public void testValidateRepsSmallNumber() {
        boolean expected = true;
        boolean actual = Workout.validateReps("1");
        assertEquals(expected, actual);
    }

    @Test public void testValidateRepsLargeNumber() {
        boolean expected = true;
        boolean actual = Workout.validateReps("100");
        assertEquals(expected, actual);
    }

    @Test public void testValidateRepsZero() {
        boolean expected = false;
        boolean actual = Workout.validateReps("0");
        assertEquals(expected, actual);
    }

    @Test public void testValidateRepsOutOfBounds() {
        boolean expected = false;
        boolean actual = Workout.validateReps("101");
        assertEquals(expected, actual);
    }

    @Test public void testValidateRepsNegativeNumber() {
        boolean expected = false;
        boolean actual = Workout.validateReps("-1");
        assertEquals(expected, actual);
    }

    @Test public void testValidateRepsInvalidString() {
        boolean expected = false;
        boolean actual = Workout.validateReps("invalidString");
        assertEquals(expected, actual);
    }

    @Test public void testValidateRepsEmptyString() {
        boolean expected = false;
        boolean actual = Workout.validateReps("");
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------

    // Test functions for createSet
    @Test public void testCreateSet() {
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("weightLifted", 25.2F);
        expected.put("reps", 10);
        HashMap<String, Object> actual = Workout.createSet(25.2F, 10);
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------

    // Test functions for createExercise
    @Test public void testCreateExercise() {
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("exerciseName", "Preacher Curl");
        ArrayList<HashMap<String, Object>> setsData = new ArrayList<>();
        expected.put("sets", setsData);
        HashMap<String, Object> actual = Workout.createExercise("Preacher Curl", setsData);
        assertEquals(expected, actual);
    }

    // ----------------------------------------------------------------

    // Test functions for createWorkout
    @Test public void testCreateWorkout() {
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("workoutPlan", "push");
        ArrayList<HashMap<String, Object>> exercisesData = new ArrayList<>();
        expected.put("exercises", exercisesData);
        HashMap<String, Object> actual = Workout.createWorkout("push", exercisesData);
        assertEquals(expected, actual);
    }
}
