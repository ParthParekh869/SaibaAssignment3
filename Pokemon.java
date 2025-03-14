import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Pokemon {
    private String name;
    private int level;
    private Stat statistics;
    private int currentHP;
    private ArrayList<Type> types;
    private ArrayList<Move> moves;
    private static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

    public Pokemon(String name, int level, Stat statistics){
        this.name = name;
        this.level = level;
        this.statistics = statistics;
        this.currentHP = statistics.getHP();
        this.types = new ArrayList<Type>();
        this.moves = new ArrayList<Move>();
        pokemons.add(this);
    }
    public boolean isFainted(){
        return currentHP <= 0;
    }
    public boolean takeDamage(int damage){
        currentHP -= damage;
        if (isFainted()) {
            statistics.recordLoss();
            return true;
        }
        return false;
    }
    public void attack(Pokemon opponent, Move move){
        Random randObj = new Random();
        int damage = 0;
        int hitChance = randObj.nextInt(101);
        if (hitChance <= move.getAccuracy()){
            damage = move.getPower();
            opponent.takeDamage(damage);
            System.out.printf("%s used %s! It dealt %d damage!\n", name, move.getMoveName(), damage);
            if (opponent.isFainted()){
                statistics.recordWin();
                opponent.statistics.recordLoss();
                System.out.printf("%s fainted!\n", opponent.getName());
            }
        }
        else {
            System.out.printf("%s used %s and missed.\n", name, move.getMoveName());
        }
    }

    public String getName() {
        return name;
    }
    public String getHP() {
        return String.format("%d/%d",currentHP,statistics.getHP());
    }
    public int getSpeed() {
        return statistics.getSpeed();
    }
    public ArrayList<Move> getMoves() {
        return moves;
    }
    public boolean isFasterThan(Pokemon opponent){
        if (opponent.statistics.getSpeed() == statistics.getSpeed()){
            Random randObj = new Random();
            int randNum = randObj.nextInt(1);
            return randNum == 0;
        }
        return statistics.getSpeed() > opponent.statistics.getSpeed();
    }

    public String toString(){
        return String.format("%s (HP: %s, Speed: %d, Level: %d, Types: %s)", name, getHP(), statistics.getSpeed(), level, types);
    }

    public void addType(Type type){
        types.add(type);
        type.addPokemon(this);
    }
    public void addMove(Move move){
        moves.add(move);
        move.addPokemon(this);
    }
    public String toStringMoves(){
        String result = "{";
        for (int i = 0; i < moves.size(); i++) {
            if (i != moves.size() - 1) {
                result += moves.get(i).getMoveName() + ", ";
            } else {
                result += moves.get(i).getMoveName();
            }
        }
        result += "}";
        return result;
    }

    public String toStringTypes(){
        String result = "{";
        for (int i = 0; i < types.size(); i++) {
            if (i != types.size() - 1) {
                result += types.get(i).getTypeName() + ", ";
            } else {
                result += types.get(i).getTypeName();
            }
        }
        result += "}";
        return result;
    }

    public void restoreHP(){
        currentHP = statistics.getHP();
    }

    public String getStat(){
        String result = "";
        result += name;
        result += "\n \t -Level: " + level;
        result += "\n \t -HP: " + statistics.getHP();
        result += "\n \t -Wins: " + statistics.getWins();
        result += "\n \t -Losses: " + statistics.getLosses();
        result += "\n \t -Attack: " + statistics.getAttack();
        result += "\n \t -Defense: " + statistics.getDefense();
        result += "\n \t -Speed: " + statistics.getSpeed();
        result += "\n \t -Types: " + toStringTypes(); // needs to have "[]"
        result += "\n \t -Moves: " + toStringMoves(); // needs to have "[]"
        return result;
    }

    // static methods
    public static ArrayList<Pokemon> getPokemons(){
        return pokemons;
    }
    public static void displayPokemonStats(){
        System.out.println("Pokemons Stats:");
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            System.out.printf("%d. %s\n", i + 1, pokemon.getStat());
        }
    }

    public static void restoreAllHP(){
        for (int i = 0; i < pokemons.size(); i++) {
            pokemons.get(i).restoreHP();
        }
    }

    public static void loadPokemons(){
        try{
            FileReader fileReader = new FileReader("pokemon-data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line= bufferedReader.readLine();
            while((line = bufferedReader.readLine())!= null){
                String[] parameters = line.split(",");
                String name = parameters[0];
                String type1 = parameters[1].trim();
                String type2 = parameters[2].trim();
                int level = Integer.parseInt(parameters[3].trim());
                int hp = Integer.parseInt(parameters[4].trim());
                int attack = Integer.parseInt(parameters[5].trim());
                int defense = Integer.parseInt(parameters[6].trim());
                int speed = Integer.parseInt(parameters[7].trim());
                String move1 = parameters[8].trim();
                String move2 = parameters[9].trim();
                String move3 = parameters[10].trim();
                String move4 = parameters[11].trim();

                Type type1obj = Type.findType(type1);
                Type type2obj = null;
                if (!type2.isEmpty()){
                    type2obj = Type.findType(type2);
                }

                Stat stat = new Stat(hp, attack, defense, speed);
                Pokemon pokemon = new Pokemon(name, level, stat);

                pokemon.addType(type1obj);
                if (type2obj != null){
                    pokemon.addType(type2obj);
                }

                Move move1obj = Move.findMove(move1);
                Move move2obj = Move.findMove(move2);
                Move move3obj = Move.findMove(move3);
                Move move4obj = Move.findMove(move4);
                pokemon.addMove(move1obj);
                pokemon.addMove(move2obj);
                pokemon.addMove(move3obj);
                pokemon.addMove(move4obj);
            }
        }
        catch (IOException e) {
            System.out.println("'pokemon-data.txt' not found");
        }
    }

}
