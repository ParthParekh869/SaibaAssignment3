import java.util.ArrayList;

public class PokemonTeam {
    private ArrayList<Pokemon> pokemons;
    
    public PokemonTeam() {
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    public void removePokemon(int index) {
        pokemons.remove(index);
    }

    public int size() {
        return pokemons.size();
    }

    public void updatePokemon(){
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            if (pokemon.isFainted()){
                pokemons.remove(i);
                i--;
            }
        }
    }
    
    public ArrayList<Pokemon> getTeam() {
        return pokemons;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            result += String.format("%d. %s\n", i + 1, pokemon);
        }
        return result;
    }

    public void clearTeam() {
        for (int i = 0; i < pokemons.size(); i++) {
            pokemons.remove(i);
            i--;
        }
    }




}
