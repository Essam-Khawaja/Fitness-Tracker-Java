/**
 * This is the main class from which the program is run
 * It also holds the menu functionality
 * Due -> March 1st 2025
 * Tutorial -> T01
 * @author Syed Essam Uddin Khawaja
 * @author Ali Gad
 * @author Abdullah Al-Dhaibani
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            Menu.startApp();
        } else {
            Menu.commandLineStartApp(args[0]);
        }
    }
}
