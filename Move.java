import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Move {

    private String moveName;
    private String moveType;
    private String category;
    private int power;
    private int accuracy;
    private ArrayList<Pokemon> pokemons;
    private static ArrayList<Move> moveList = new ArrayList<Move>();

    public Move(String moveName, String moveType, String category, int power, int accuracy) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pokemons = new ArrayList<Pokemon>();
        moveList.add(this);
    }

    public String getMoveName() {
        return moveName;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String getMoveType() {
        return moveType;
    }

    public void addPokemon(Pokemon p) {
        pokemons.add(p);
    }

    public String getPokemonsToString(){
        String result = "Pokemons : [ ";
        for (int i = 0; i < pokemons.size(); i++) {
            if (i != pokemons.size() - 1) {
                result += pokemons.get(i).getName() + ", ";
            } else {
                result += pokemons.get(i).getName();
            }
        }
        result += " ]";
        return result;
    }

    public String toString(){
        return String.format("%s(MoveType: %s, Category: %s, Power: %d, Accuracy: %d)", moveName, moveType, category, power, accuracy);
    }

    // static methods

    public static ArrayList<Move> getMoves() {
        return moveList;
    }

    public static void loadMoves(){
        try{
            FileReader fileReader = new FileReader("moves-data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line= bufferedReader.readLine();
            while((line = bufferedReader.readLine())!= null){
                String[] parameters = line.split(",");
                String moveName = parameters[0].trim();
                String moveType = parameters[1].trim();
                String category = parameters[2].trim();
                int power = Integer.parseInt(parameters[3]);
                int accuracy = Integer.parseInt(parameters[4]);
                new Move(moveName, moveType, category, power, accuracy);
            }
        }
        catch (IOException e) {
            System.out.println("'moves-data.txt' not found");
        }
    }

    public static Move findMove(String moveName){
        for (int i = 0; i < moveList.size(); i++) {
            if (moveList.get(i).getMoveName().equals(moveName)) {
                return moveList.get(i);
            }
        }
        return null;
    }

    public static void displayMoveStats(){
        for (int i = 0; i < moveList.size(); i++) {
            Move move = moveList.get(i);
            System.out.println(move);
            System.out.println(move.getPokemonsToString());
        }
    }



}
