Fitness Tracker App

🧾 Information
Made by: Ali Gad, Abdullah Al-Dhaibani, Syed Essam Uddin Khawaja
Course: CPSC 233 - Tutorial 01
Supervisor: Tarif Ashraf

This project is a JavaScript-based fitness tracker app created to help users monitor 
and manage their fitness journey. It includes features like calorie tracking, workout 
logging, and interactive data views. The app was developed as part of a university 
course to showcase fundamental programming, UI design, and user interaction.
-------------------------------------------------------------------------------------

💪 What It Does
The Fitness Tracker App provides:

    User Authentication

    Calorie Tracking

    Workout Logging

    Data Visualization and Summary

------------------------------------------------------------------------------------

⚙️ How to Run It

Run the following command in your terminal. Make sure to update the module path to where your javafx is downloaded on your device.

```bash 
java --module-path "C:\Users\abdul\Downloads\openjfx-24_windows-x64_bin-sdk\javafx-sdk-24\lib" --add-modules
javafx.controls,javafx.fxml -jar CPSC233Project.jar
```

------------------------------------------------------------------------------------

🕹️ How to Interact
Login Screen
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

    Login and interface designed by: Essam Khawaja

➤ Calories
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

➤ Workout
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

➤ View Data
This section provides summary statistics and visual comparisons:

    Total workout volume

    Meal vs. Snack comparison

    Access to all saved data in a structured view

    Developed by: Essam Khawaja