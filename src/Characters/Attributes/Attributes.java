package Characters.Attributes;

public class Attributes {
    protected int strength;
    protected int speed;
    protected int intelligence;
    protected int charisma;
    protected int hp;

    public Attributes(int strength, int speed, int intelligence, int charisma, int hp) {
        this.strength = strength;
        this.speed = speed;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getHp() {
        return hp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void lowerHp(int dmg) {
        this.setHp(this.getHp() - dmg);
    }
}
