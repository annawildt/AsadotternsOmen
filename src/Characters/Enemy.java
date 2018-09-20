package Characters;

import Characters.Attributes.Attributes;

public class Enemy extends Character {
    private String name;

    public Enemy(String name, int strength, int speed, int intelligence, int charisma, int hp) {
        this.name = name;
        this.attributes = new Attributes(strength, speed, intelligence, charisma, hp);
    }

    public String getName() {
        return name;
    }
}
