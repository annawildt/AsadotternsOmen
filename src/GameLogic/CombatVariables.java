package GameLogic;

import Characters.Character;
import Characters.Attributes.AttributeEnum;
import Characters.Skills.Skill;

public class CombatVariables {
    private double power;
    private double abilityModifier;

    public CombatVariables(Character character, AttributeEnum attributeEnum, Skill skill) {
        this.power = character.getAttribute(attributeEnum) + skill.getCombatMod();
    }

    public CombatVariables(Character character, AttributeEnum attributeEnum) {
        this.power = character.getAttribute(attributeEnum);
    }

    public double getPower(){
        return power;
    }

    public double getAbilityModifier() {
        return 0.0;
    }
}
