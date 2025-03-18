import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        Workout workout = new Workout("Push", exercises);
        Calories calorie1 = new Calories("Meal", "Breakfast", "Toast", 300);

        User testUser = new User("Name2", "Password", "Email2");
        testUser.addCalorieData(calorie1);
        testUser.addWorkoutData(workout);

        Save.SaveData(testUser);
    }

    @Test public void testLoad() {
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

        Workout workout = new Workout("Push", exercises);

        Calories calorie1 = new Calories("Meal", "Breakfast", "Toast", 300);

        User actualUser = new User("Name", "Password", "Email");
        User expectedUser = new User("Name", "Password", "Email");
        expectedUser.addCalorieData(calorie1);
        expectedUser.addWorkoutData(workout);

        Save.LoadData(actualUser);
        ArrayList<Calories> actualCalories = actualUser.getCalorieData();
        ArrayList<Workout> actualWorkouts = actualUser.getWorkoutData();
        ArrayList<Calories> expectedCalories = expectedUser.getCalorieData();
        ArrayList<Workout> expectedWorkouts = expectedUser.getWorkoutData();

        System.out.println(actualCalories.toString());
        System.out.println(actualWorkouts.toString());
        System.out.println(expectedCalories.toString());
        System.out.println(expectedWorkouts.toString());
    }
}
