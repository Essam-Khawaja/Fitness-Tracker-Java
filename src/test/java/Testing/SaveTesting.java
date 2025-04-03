package Testing;

import Data.*;
import Enums.MealTime;
import Enums.MealType;
import Enums.WorkoutPlan;
import Save.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SaveTesting {
    @Test public void testSave() {
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

        Workout workout = new Workout(WorkoutPlan.PUSH, exercises);
        Calories calorie1 = new Calories(MealType.MEAL, MealTime.BREAKFAST, "Toast", 300);

        User testUser = new User("Name2", "Password", "Email2");
        testUser.addCalorieData(calorie1);
        testUser.addWorkoutData(workout);

        Save.SaveData(testUser);
        Save.SaveNewUser(testUser);
    }

    @Test public void testSaveUserMultipleData(){
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

        Workout workout = new Workout(WorkoutPlan.PUSH, exercises);
        Workout workout2 = new Workout(WorkoutPlan.PULL, exercises);

        Calories calorie1 = new Calories(MealType.MEAL, MealTime.BREAKFAST, "Toast", 300);
        Calories calorie2 = new Calories(MealType.MEAL, MealTime.LUNCH, "Biryani", 500);
        Calories calorie3 = new Calories(MealType.SNACK, MealTime.NULL, "Oreos", 200);

        User actualUser = new User("Name3", "Password3", "Email3");
        actualUser.addCalorieData(calorie1);
        actualUser.addWorkoutData(workout);
        actualUser.addWorkoutData(workout2);
        actualUser.addCalorieData(calorie2);
        actualUser.addCalorieData(calorie3);

        Save.SaveData(actualUser);
        Save.SaveNewUser(actualUser);
    }

    @Test public void testLoadOtherUser() {
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

        Workout workout = new Workout(WorkoutPlan.PUSH, exercises);
        Workout workout2 = new Workout(WorkoutPlan.PULL, exercises);

        Calories calorie1 = new Calories(MealType.MEAL, MealTime.BREAKFAST, "Toast", 300);
        Calories calorie2 = new Calories(MealType.MEAL, MealTime.LUNCH, "Biryani", 500);
        Calories calorie3 = new Calories(MealType.SNACK, MealTime.NULL, "Oreos", 200);

        User actualUser = new User("Name3", "Password3", "Email3");
        User expectedUser = new User("Name3", "Password3", "Email3");
        expectedUser.addCalorieData(calorie1);
        expectedUser.addWorkoutData(workout);
        expectedUser.addWorkoutData(workout2);
        expectedUser.addCalorieData(calorie2);
        expectedUser.addCalorieData(calorie3);

        Save.LoadData(actualUser, "src/Save/Save.csv");
        boolean expected = true;
        boolean actual = actualUser.equals(expectedUser);
        assertEquals(expected, actual);
    }

    @Test public void testLoadUserMultipleData(){
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

        Workout workout = new Workout(WorkoutPlan.PUSH, exercises);
        Workout workout2 = new Workout(WorkoutPlan.PULL, exercises);

        Calories calorie1 = new Calories(MealType.MEAL, MealTime.BREAKFAST, "Toast", 300);
        Calories calorie2 = new Calories(MealType.MEAL, MealTime.LUNCH, "Biryani", 500);
        Calories calorie3 = new Calories(MealType.SNACK, MealTime.NULL, "Oreos", 200);

        User expectedUser = new User("Name3", "Password3", "Email3");
        expectedUser.addCalorieData(calorie1);
        expectedUser.addWorkoutData(workout);
        expectedUser.addWorkoutData(workout2);
        expectedUser.addCalorieData(calorie2);
        expectedUser.addCalorieData(calorie3);

        User actualUser = new User("Name3", "Password3", "Email3");
        Save.LoadData(actualUser, "src/Save/Save.csv");

        boolean expected = true;
        boolean actual = actualUser.equals(expectedUser);
        assertEquals(expected, actual);
    }

    @Test public void SaveUser() {
        User user = new User("Name2", "Password2", "Email2");
        Save.SaveNewUser(user);
    }
}
