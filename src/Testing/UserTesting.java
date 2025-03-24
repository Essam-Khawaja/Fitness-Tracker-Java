package Testing;

import Data.*;
import Enums.WorkoutPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTesting {

    @Test
    public void testUserCreation(){
        User user = new User("user1", "password123", "user1@example.com");
        assertEquals("user1", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("user1@example.com", user.getEmail());
    }

    @Test
    public void testAddCalorieData(){
        User user = new User("user1", "password123", "user1@example.com");
                Calories calorieEntry = new Calories(500,"Lunch");
        user.addCalorieData(calorieEntry);
        assertEquals(1, user.getCalorieData().size());
        assertEquals(calorieEntry, user.getCalorieData().get(0));
    }

    @Test
    public void testAddWorkoutData(){
        User user = new User("user1", "password123", "user1@example.com");
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
        Workout workoutEntry = new Workout(WorkoutPlan.PUSH, exercises);
        user.addWorkoutData(workoutEntry);
        assertEquals(1, user.getWorkoutData().size());
        assertEquals(workoutEntry, user.getWorkoutData().get(0));
    }

    @Test
    public void testResetTrackingData(){
        User user = new User("user1", "password123", "user1@example.com");
        user.addCalorieData(new Calories(300, "Breakfast"));
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
        user.addWorkoutData(new Workout(WorkoutPlan.PUSH, exercises));
        user.resetTrackingData();
        assertTrue(user.getCalorieData().isEmpty());
        assertTrue(user.getWorkoutData().isEmpty());
    }

    @Test
    public void testEqualsMethodForIdenticalUsers(){
        User user1 = new User("user1", "password123", "user1@example.com");
        User user2 = new User("user1", "password123", "user1@example.com");
        assertTrue(user1.equals(user2));
    }

    @Test
    public void testEqualsMethodForDifferentUsers() {
        User user1 = new User("user1", "password123", "user1@example.com");
        User user2 = new User("user2", "differentPassword", "differentEmail@example.com");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void TestToStringMethod(){
        User user = new User("user1", "password123", "user1@example.com");
        String expectedString = "user1,password123,user1@example.com";
        assertEquals(expectedString, user.toString());
    }
}
