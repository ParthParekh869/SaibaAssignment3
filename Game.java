import java.util.ArrayList;

public class Game {
    private Player player1;
    private Player player2;
    private Menu menu;

    public Game() {
        this.menu = Menu.getInstance();
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void getPlayerNames(){
        System.out.print("Player 1, enter your name: ");
        String name1 = menu.getValidStringInput();
        System.out.print("Player 2, enter your name: ");
        String name2 = menu.getValidStringInput();
        Player player1 = Player.getPlayer(name1);
        Player player2 = Player.getPlayer(name2);
        setPlayer1(player1);
        setPlayer2(player2);
    }

    public void pickPokemons(){
        int noOfPicksLeft = 3;
        Pokemon.restoreAllHP();
        ArrayList<Pokemon> availablePokemons = Pokemon.getPokemons();
        while (noOfPicksLeft > 0 && availablePokemons.size() >= 2){

            System.out.println("\n" + player1.getName() + ", choose a Pok´emon for your team:");
            displayAvailablePokemons(availablePokemons);
            int choice = menu.getValidIntegerChoice(availablePokemons.size());
            Pokemon pokemon1 = availablePokemons.get(choice - 1);
            player1.getTeam().addPokemon(pokemon1);
            availablePokemons.remove(choice - 1);

            System.out.println("\n" + player2.getName() + ", choose a Pok´emon for your team:");
            displayAvailablePokemons(availablePokemons);
            choice = menu.getValidIntegerChoice(availablePokemons.size());
            Pokemon pokemon2 = availablePokemons.get(choice - 1);
            player2.getTeam().addPokemon(pokemon2);
            availablePokemons.remove(choice - 1);

            // printing the team information for each player
            System.out.println("\n" + player1.getName() + " Team:");
            System.out.println(player1.getTeam());
            System.out.println(player2.getName() + " Team:");
            System.out.println(player2.getTeam());
        }
        
    }
    private void displayAvailablePokemons(ArrayList<Pokemon> availablePokemons) {
        for (int i = 0; i < availablePokemons.size(); i++) {
            System.out.println((i + 1) + ". " + availablePokemons.get(i));
        }
    }

    public Pokemon sendPokemon(Player player){
        System.out.println(player.getName() + ", send a Pok´emon for this round:");
        player.getTeam().updatePokemon();
        System.out.println(player.getTeam());
        int choice = menu.getValidIntegerChoice(player.getTeam().size());
        return player.getTeam().getPokemon(choice - 1);
    }

    public Move choosePokemonMove(Player player, Pokemon pokemon){
        System.out.println(player.getName() + ", choose the move for " + pokemon.getName() + ":");
        displayPokemonMoves(pokemon.getMoves());
        int choice = menu.getValidIntegerChoice(pokemon.getMoves().size());
        return pokemon.getMoves().get(choice - 1);
    }
    private void displayPokemonMoves(ArrayList<Move> moves) {
        for (int i = 0; i < moves.size(); i++) {
            System.out.println((i + 1) + ". " + moves.get(i));
        }
    }

    public void startBattle(){
        System.out.println("###### BATTLE STARTS #######");
        Pokemon player1Pokemon = sendPokemon(player1);
        Pokemon player2Pokemon = sendPokemon(player2);


    }

    public void simulateFight(Pokemon player1Pokemon, Pokemon player2Pokemon){
        System.out.printf("###### %s VS. %s #######\n", player1Pokemon.getName(), player2Pokemon.getName());
        Pokemon fasterPokemon;
            Pokemon slowerPokemon;
            if (player1Pokemon.getSpeed() >= player2Pokemon.getSpeed()){
                fasterPokemon = player1Pokemon;
                slowerPokemon = player2Pokemon;
            }
            else {
                fasterPokemon = player2Pokemon;
                slowerPokemon = player1Pokemon;
            }
        while (!player1Pokemon.isFainted() && !player2Pokemon.isFainted()){
            System.out.printf("%s is faster than %s! So it attacks first!\n", fasterPokemon.getName(), slowerPokemon.getName());
            

            
        }
    }
}
