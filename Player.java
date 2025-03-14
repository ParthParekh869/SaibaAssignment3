import java.util.ArrayList;

public class Player {
    private String name;
    private PokemonTeam team;
    private int wins;
    private int losses;

    private static ArrayList<Player> players = new ArrayList<Player>();


    public Player(String name) {
        this.name = name;
        this.team = new PokemonTeam();
        this.wins = 0;
        this.losses = 0;
        players.add(this);
    }

    public String getName() {
        return name;
    }

    public PokemonTeam getTeam() {
        return team;
    }

    public void wonAgainst(Player opponent) {
        wins++;
        opponent.losses++;
    }

    public String toString() {
        return String.format("%s (Wins: %d, Losses: %d)", name, wins, losses);
    }

    public static Player getPlayer(String name) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return new Player(name);
    }

    public static void displayPlayerStats(){
        System.out.println("\nPlayer Stats:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.printf("\t %d. %s\n", i + 1, player);
        }
        System.out.println();
    }

    public static void clearPlayer(){
        for (int i = 0; i < players.size(); i++) {
            players.remove(i);
            i--;
        }
    }
}
