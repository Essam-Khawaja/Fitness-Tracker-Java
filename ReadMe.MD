# Fitness Tracker App

## 🧾 Course Information and Authors

Made by: Ali Gad, Abdullah Al-Dhaibani, Syed Essam Uddin Khawaja

Course: CPSC 233 - Tutorial 01

Supervisor: Tarif Ashraf

## 🚀 Program Description
This project is a JavaScript-based fitness tracker app created to help users monitor 
and manage their fitness journey. It includes features like calorie tracking, workout 
logging, and interactive data views. The app was developed as part of a university 
course to showcase fundamental programming, UI design, and user interaction.
-------------------------------------------------------------------------------------
## 💪 What It Does
### The Fitness Tracker App provides:

    User Authentication

    Calorie Tracking

    Workout Logging

    Data Visualization and Summary
------------------------------------------------------------------------------------
## ⚙️ How to Execute the Program
The app is packaged as a `.jar` file and requires **JavaFX** to run.

### Prerequisites:
1. Java JDK 17+ installed.
2. JavaFX SDK downloaded (compatible version).

### Steps to Run:
1. Open Command Prompt/Terminal.
2. Navigate to the directory containing `CPSC233Project.jar`.
3. Run the following command (update the JavaFX path to match your system):

```bash 
java --module-path "C:\Users\abdul\Downloads\openjfx-24_windows-x64_bin-sdk\javafx-sdk-24\lib" --add-modules javafx.controls,javafx.fxml -jar CPSC233Project.jar
```
------------------------------------------------------------------------------------
## 🕹️ How to Interact
### ➤ Login Screen
Upon launching the app, a login screen will appear asking for:

    Email

    Username

    Password
    Once logged in, you will be asked if you want to load previously saved data.

    Main Interface
    After login, the top-left corner of the screen displays three buttons:

    Calories

    Workout

    View Data

    Login and interface designed by: Syed Essam Uddin Khawaja

### ➤ Calories
Add Calorie Data:

    Choose between Meal or Snack.

    If Snack is selected: the meal time option is disabled.

    If Meal is selected: select from Breakfast, Lunch, or Dinner.

    Input fields include:

    Snack or Meal Name

    Calories

    (Optional) Meal Time

    View Calorie Data:

    Displays all logged calorie entries.

    Developed by: Abdullah Al-Dhaibani

### ➤ Workout
Add Workout Data:

    Choose from six workout types:

    Push, Pull, Legs, Upper Body, Lower Body, Full Body

    Input required:

    Exercise Name

    Weight Lifted

    Number of Reps

    Number of Sets

    View Workout Data:

    Each workout category displays the corresponding logged entries.

    Developed by: Ali Gad

### ➤ View Data
This section provides summary statistics and visual comparisons:

    Total workout volume

    Meal vs. Snack comparison

    Access to all saved data in a structured view

    Developed by: Syed Essam Uddin Khawaja

------------------------------------------------------------------------------------

## ℹ️ Class Information

### Workout Class
  - Stores the workout by storing its plan and all exercises.
  - The plan itself is a string, while exercises are stored as an ArrayList of an Exercise object
  - There are also some helper static functions to validate the inputs, while also having the regular getters, setters, toString, and equals

### Exercise Class
  - Stores the exercise by storing the name of the exercise, and all the sets of the exercise.
  - The name is stored as a String, while sets are stored as an ArrayList of a Set object
  - Has all the regular getters, setters, toString, equals and hashCode functions

### Set Class
  - Stores the weight lifted and reps for the set
  - The weight lifted is stored as a float, while reps are stored as an integer
  - Has all the regular getters, setters, toString, equals and hashCode functions

### Calories Class
  - Stores all the following properties of the calorie entry:
    - snackOrMeal: Stores whether the entry is a snack or a meal; stored as an enum
    - mealTime: Stores the time of the meal: Breakfast, Lunch, Dinner. Stored as an enum
    - foodName; Stores the name of the meal; stored as a String
    - calories: Stores the calories of the meal; stored as an integer
  - Has all the regular getters, setters, toString, equals and hashCode functions

### User Class
  - Stores all the user data:
    - username: String to store the username of User  
    - password: String to store the password of User
    - email: String to store the email of User
    - workoutData: ArrayList of Workouts for all the User workouts
    - calorieData: ArrayList of Calories for all the User calorie entries
  - Has all the regular getters, setters, toString, equals and hashCode functions

### Save Class (static helper class)
  - Has all the functionality needed to handle the saving within the program
  - Helps load data, save data, as well as validate and find data in the save files.
