package Characters.Skills;

import Characters.Attributes.AttributeEnum;
import Characters.ItemSkillSuper;

public class Skill extends ItemSkillSuper {
    protected int unlockedLevel;
    protected double combatMod;
    protected int skillLevel;
    protected boolean skillIsMaxed;

    public Skill(String skillName, String skillDescription, AttributeEnum attributeEnum, double combatMod, int unlockedLevel) {
        this.name = skillName;
        this.description = skillDescription;
        this.attributeEnum = attributeEnum;
        this.combatMod = combatMod;
        this.unlockedLevel = unlockedLevel;
        this.skillLevel = 1;
        this.skillIsMaxed = false;

    }

    public boolean isSkillIsMaxed() {
        return skillIsMaxed;
    }

    public void setSkillIsMaxed() {
        this.skillIsMaxed = true;
    }

    public int getUnlockedLevel() {
        return unlockedLevel;
    }

    public double getCombatMod() {
        return combatMod;
    }

    public int getSkillLevel() { return skillLevel;}

    public void incrementSkill() {
        this.skillLevel++;
    }
}
