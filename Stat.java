public class Stat {
    private int HP;
    private int attack;
    private int defense;
    private int speed;
    private int wins;
    private int losses;

    public Stat(int hp, int attack, int defense, int speed) {
        this.HP = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.wins = 0;
        this.losses = 0;
    }

    public int getHP() {
        return HP;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getSpeed() {
        return speed;
    }
    // setters
    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void recordWin() {
        wins++;
    }
    public void recordLoss() {
        losses++;
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public String toString() {
        return String.format("HP: %d, Attack: %d, Defense: %d, Speed: %d", HP, attack, defense, speed);
    }
}
