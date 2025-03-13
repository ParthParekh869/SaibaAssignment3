import java.util.ArrayList;

public class Type {
    private String typeName;
    private ArrayList<Pokemon> pokemons;
    private static ArrayList<Type> types = new ArrayList<Type>();

    public Type(String typeName) {
        this.typeName = typeName;
        this.pokemons = new ArrayList<Pokemon>();
        types.add(this);
    }

    public String getTypeName() {
        return typeName;
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public String getPokemonstoString() {
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

    public String toString() {
        return typeName;
    }
    public static ArrayList<Type> getTypes() {
        return types;
    }

    public static void displayTypeStats(){
        for (int i = 0; i < types.size(); i++) {
            Type type = types.get(i);
            System.out.printf("%d. %s: %s\n", i + 1, type, type.getPokemonstoString());
        }
    }

    public static Type findType(String typeName){
        for (int i = 0; i < types.size(); i++) {
            Type type = types.get(i);
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        // if we reach here, the type was not found
        // we will add create a new type and return it
        return new Type(typeName);
    }
}
