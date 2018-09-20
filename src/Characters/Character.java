package Characters;

import Characters.Attributes.AttributeEnum;
import Characters.Attributes.Attributes;

public abstract class Character {
    protected Attributes attributes;

    public Attributes getAttributes() {
        return this.attributes;
    }

    public int getAttribute(AttributeEnum attributeEnum) {
        int attribute = 0;
        switch (attributeEnum) {
            case STRENGTH:
                attribute = attributes.getStrength();
                break;
            case SPEED:
                attribute = attributes.getSpeed();
                break;
            case CHARISMA:
                attribute = attributes.getCharisma();
                break;
            case INTELLIGENCE:
                attribute = attributes.getIntelligence();
                break;
        }
        return attribute;
    }

}
