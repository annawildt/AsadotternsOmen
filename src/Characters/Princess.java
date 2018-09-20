package Characters;

import Characters.Attributes.Attributes;
import Characters.Skills.CharacterSkills;
import Characters.Skills.Skill;
import Characters.Skills.Skills;
import Repository.Repository;

import java.util.List;

public class Princess extends Character {
    private int trainingPoints;
    private CharacterSkills characterSkills;

    public Princess(Repository repo, int strength, int speed, int intelligence, int charisma, int hp, int trainingPoints, Skills skillsList) {
        this.attributes = new Attributes(strength, speed, intelligence, charisma, hp);
        this.trainingPoints = trainingPoints;
        this.characterSkills = new CharacterSkills(repo, this.attributes);
    }

    public int getTrainingPoints() {
        return trainingPoints;
    }

    public void setTrainingPoints(int trainingPoints) {
        this.trainingPoints = trainingPoints;
    }

    public CharacterSkills getCharacterSkills() {
        return this.characterSkills;
    }

    public List<Skill> getCharacterSkillList() {
        return this.characterSkills.getCharacterSkillList();
    }

    public void addStrength(int strength) {
        this.attributes.setStrength(this.attributes.getStrength() + strength);
    }

    public void addSpeed(int speed) {
        this.attributes.setSpeed(this.attributes.getSpeed() + speed);
    }

    public void addIntelligence(int intelligence) {
        this.attributes.setIntelligence(this.attributes.getIntelligence() + intelligence);
    }

    public void addCharisma(int charisma) {
        this.attributes.setCharisma(this.attributes.getCharisma() + charisma);
    }

}
