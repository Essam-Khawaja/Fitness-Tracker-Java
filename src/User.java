import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private ArrayList<Workout> workoutData = new ArrayList<>();
    private ArrayList<Calories> calorieData = new ArrayList<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void addCalorieData(Calories newData) {
        // Will add the calorie stuff here
        calorieData.add(newData);
    }

    public void addWorkoutData(Workout newData) {
        // Will add workout stuff here in the future
        workoutData.add(newData);
    }

    public void resetTrackingData() {
        calorieData.clear();
        workoutData.clear();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Workout> getWorkoutData() {
        return workoutData;
    }
    public ArrayList<Calories> getCalorieData() {
        return calorieData;
    }
}
