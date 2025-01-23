/**
 * The main entry point for the application. 
 * This class is responsible for starting the game by invoking the `menu` method of the `Menu` class.
 */
public class Main {

    /**
     * The main method that starts the application.
     * It calls the `menu` method from the `Menu` class to initiate the game's menu system.
     *
     * @param args Command-line arguments (not used in this class).
     */
    public static void main(String[] args) {
        Menu.menu();  // Start the game's menu system
    }
}
