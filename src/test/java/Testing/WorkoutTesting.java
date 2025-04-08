package Testing;

import Data.Exercise;
import Data.Set;
import Data.Workout;
import Enums.WorkoutPlan;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WorkoutTesting {
    // Testing functions for validateWorkoutPlan()
    @Test public void testValidateWorkoutPlanPush() {
        boolean expected = true;
        boolean actual = Workout.validateWorkoutPlan("1");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWorkoutPlanUpperAllLower() {
        boolean expected = true;
        boolean actual = Workout.validateWorkoutPlan("3");
        assertEquals(expected, actual);
    }

    @Test public void testValidateWorkoutPlanLegAllUpper() {
        boolean expected = true;
        boolean actual = Workout.validateWorkoutPlan("2");
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

    // Testing functions for equals()
    @Test public void testWorkoutEqual() {
        Set set1 = new Set(10, 25.5F);
        Set set2 = new Set(10, 25.5F);
        ArrayList<Set> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        Exercise exercise1 = new Exercise("Preacher Curl", sets);
        Exercise exercise2 = new Exercise("Bench Press", sets);
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        Workout workout1 = new Workout(WorkoutPlan.PUSH, exercises);
        Workout workout2 = new Workout(WorkoutPlan.PUSH, exercises);
        boolean actual = workout1.equals(workout2);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test public void testWorkoutNotEqual() {
        Set set1 = new Set(10, 25.5F);
        Set set2 = new Set(10, 25.5F);
        ArrayList<Set> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        Exercise exercise1 = new Exercise("Preacher Curl", sets);
        Exercise exercise2 = new Exercise("Bench Press", sets);
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        Workout workout1 = new Workout(WorkoutPlan.PUSH, exercises);
        Workout workout2 = new Workout(WorkoutPlan.PULL, exercises);
        boolean actual = workout1.equals(workout2);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
