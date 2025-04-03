package Data;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents user data
 * Stores all the data in the menu, using help from other classes and enums
 * @author Syed Essam Uddin Khawaja
 */
public class User {
    private String username;    // Stores the username of the user
    private String password;    // Stores the password of the user
    private String email;   // Stores the email of the user
    private ArrayList<Workout> workoutData = new ArrayList<>();     // Stores all the calorie data of the user
    private ArrayList<Calories> calorieData = new ArrayList<>();    // Store all the workout data of the user

    /**
     * The constructor of the User class
     * @param username -> the username of the new user
     * @param password -> the password of the new user
     * @param email -> the email of the new user
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Adds a new calorie object into the calorieData array
     * @param newData -> New Data to be added
     */
    public void addCalorieData(Calories newData) {
        calorieData.add(newData);
    }

    /**
     * Adds a new workout object into workoutData array
     * @param newData -> New data to be added
     */
    public void addWorkoutData(Workout newData) {
        workoutData.add(newData);
    }

    /**
     * Reset all the data in the user
     */
    public void resetTrackingData() {
        calorieData.clear();
        workoutData.clear();
    }

    /**
     * Set the username
     * @param username -> new username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the password
     * @param password -> New password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the email
     * @param email -> New email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return - the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return -> the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return -> the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return -> The arraylist of all the workout data
     */
    public ArrayList<Workout> getWorkoutData() {
        return workoutData;
    }

    /**
     * @return -> The arraylist of all the calorie data
     */
    public ArrayList<Calories> getCalorieData() {
        return calorieData;
    }

    /**
     * Takes another user and checks if its equal
     * @param o -> The user to compare with
     * @return -> Boolean of whether the user is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(workoutData, user.workoutData) &&
                Objects.equals(calorieData, user.calorieData);
    }

    /**
     * @return -> the hashcode of the object instance of the class
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, workoutData, calorieData);
    }

    /**
     * @return -> The string of all the details in the class; mainly to use in saving
     */
    @Override
    public String toString() {
        return username + "," + password + "," + email;
    }
}
