import java.util.*;;
public class Menu {
    private static Menu instance = null;

    private Scanner scanner;

    private Menu() {}

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("Welcome to the Pokémon Game!");
        System.out.println("1. Play Game");
        System.out.println("2. Get Pokémon Stats");
        System.out.println("3. Get Player Stats");
        System.out.println("4. Get Type Stats");
        System.out.println("5. Get Move Stats");
        System.out.println("6. Exit");
    }

    public int getValidIntegerChoice(int max){
        final int MINIMUM_VALUE = 1;
        int choice = -1; // invalid choice
        boolean recievedValidInput = false;
        while (!recievedValidInput && scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (choice >= MINIMUM_VALUE && choice <= max) {
                recievedValidInput = true;
                scanner.nextLine(); // consume newline character
            } else {
                System.out.println("Please enter a number between " + MINIMUM_VALUE + " and " + max + ".");
                scanner.next(); // consume invalid input
            }
        }
        return choice;
    }

    public void runMenu(){
        boolean exit = false;
        while(!exit){
            displayMenu();
            int choice = getValidIntegerChoice(6);

            if (choice == 1){
                System.out.println("Starting the game...");
            } 
            else if (choice == 2){
                System.out.println("Displaying Pokémon stats...");
                Pokemon.displayPokemonStats();
            } 
            else if (choice == 3){
                System.out.println("Displaying player stats...");
            } 
            else if (choice == 4){
                System.out.println("Displaying type stats...");
            } 
            else if (choice == 5){
                System.out.println("Displaying move stats...");
            } 
            else if (choice == 6){
                System.out.println("Exiting the game. Goodbye!");
                exit = true;
            }
            else{
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public String getValidStringInput() {
        boolean recievedValidInput = false;
        String input = "";
        while (!recievedValidInput) {
            input = scanner.next();
            if (!input.isEmpty()) {
                boolean hasOnlyLetters = true;
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isLetter(input.charAt(i))) {
                        hasOnlyLetters = false;
                    }
                }
                if (hasOnlyLetters) {
                    recievedValidInput = true;
                }
                
            } else {
                System.out.println("Please enter a non-empty string.");
            }
        }
        return input;
    }





}
