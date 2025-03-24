package Testing;

import org.junit.jupiter.api.Test;

import Data.User;
import Data.Calories;
import Data.Workout;

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
        Workout workoutEntry = new Workout("Running", 300);
                user.addWorkoutData(workoutEntry);
        assertEquals(1, user.getWorkoutData().size());
        assertEquals(workoutEntry, user.getWorkoutData().get(0));
    }

    @Test
    public void testResetTrackingData(){
        User user = new User("user1", "password123", "user1@example.com");
        user.addCalorieData(new Calories(300, "Breakfast"));
        user.addWorkoutData(new Workout("Yoga", 200));
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
