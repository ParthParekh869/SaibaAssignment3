import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

// Main class
// Should not be modified, use as it is provided
public class Main { 
    public static void main(String[] args) {
        Move.loadMoves();
        Pokemon.loadPokemons();

        Menu menu = Menu.getInstance();
        Scanner scnr = new Scanner(System.in);
        menu.setScanner(scnr);
        menu.runMenu();
        scnr.close();
    }
}