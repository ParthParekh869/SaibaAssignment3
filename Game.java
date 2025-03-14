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
        player1 = Player.getPlayer(name1);
        player2 = Player.getPlayer(name2);
    }

    public void pickPokemons(){
        int noOfPicksLeft = 3;
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

            noOfPicksLeft--;
        }
        
    }
    private void displayAvailablePokemons(ArrayList<Pokemon> availablePokemons) {
        for (int i = 0; i < availablePokemons.size(); i++) {
            System.out.println((i + 1) + ". " + availablePokemons.get(i));
        }
    }

    public Pokemon sendPokemon(Player player){
        System.out.println(player.getName() + ", send a Pok´emon for this round:");
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
        while (player1.getTeam().size() > 0 && player2.getTeam().size() > 0){
            Pokemon player1Pokemon = sendPokemon(player1);
            Pokemon player2Pokemon = sendPokemon(player2);
            simulateFight(player1Pokemon, player2Pokemon);
            player1.getTeam().updatePokemon();
            player2.getTeam().updatePokemon();
        }
        Pokemon.restoreAllHP();
        System.out.println("Game Over!");
        if (player1.getTeam().size() == 0){
            System.out.println(player2.getName() + " Team Wins!");
            player2.wonAgainst(player1);
        } 
        else {
            System.out.println(player1.getName() + " Team Wins!");
            player1.wonAgainst(player2);
        }
    }

    public void simulateFight(Pokemon player1Pokemon, Pokemon player2Pokemon){

        Pokemon fasterPokemon;
        Pokemon slowerPokemon;
        Player fasterPlayer;
        Player slowerPlayer;
    
        if (player1Pokemon.isFasterThan(player2Pokemon)) {
            fasterPokemon = player1Pokemon;
            slowerPokemon = player2Pokemon;
            fasterPlayer = player1;
            slowerPlayer = player2;
        } else {
            fasterPokemon = player2Pokemon;
            slowerPokemon = player1Pokemon;
            fasterPlayer = player2;
            slowerPlayer = player1;
        }

        System.out.println("###### BATTLE STARTS #######");
        System.out.printf("######  %s VS. %s  #######\n", player1Pokemon.getName(), player2Pokemon.getName());
        System.out.println("\n" + fasterPokemon.getName() + " is faster than " + slowerPokemon.getName()+ "! So it attacks first!");
        System.out.println("###### Pok´emon STATS #######");
        System.out.println(player1Pokemon);
        System.out.println(player2Pokemon);

        while (!player1Pokemon.isFainted() && !player2Pokemon.isFainted()){
            Move move = choosePokemonMove(fasterPlayer, fasterPokemon);
            fasterPokemon.attack(slowerPokemon, move);
            if (!slowerPokemon.isFainted()){
                move = choosePokemonMove(slowerPlayer, slowerPokemon);
                slowerPokemon.attack(fasterPokemon, move);
            }
        }
    }
}
