package TrainingLogic;

import Characters.CharacterConstants;
import Characters.Attributes.AttributeEnum;
import Characters.Skills.CharacterSkills;
import Characters.Skills.Skill;
import Characters.Princess;

public class TrainingLogic {
    public void trainPrincess(Princess princess, CharacterSkills characterSkills, Skill skill) {
        checkSkillAndRemoveCost(princess, skill);
        addStatToPrincess(princess, skill);
        updateSkills(princess, characterSkills, skill);

    }

    private void checkSkillAndRemoveCost(Princess princess, Skill skill) {
        if (skill.getSkillLevel() < CharacterConstants.SKILL_MAX_LEVEL) {
            if (princess.getTrainingPoints() >= CharacterConstants.SKILL_COST) {
                skill.incrementSkill();
                princess.setTrainingPoints(princess.getTrainingPoints() - CharacterConstants.SKILL_COST);
            }
        }
    }
    private void addStatToPrincess(Princess princess, Skill skill) {
        if (skill.getSkillLevel() == CharacterConstants.SKILL_MAX_LEVEL && !skill.isSkillIsMaxed()) {
            AttributeEnum ae = skill.getAttributeEnum();
            switch (ae) {
                case STRENGTH:
                    princess.addStrength(1);
                    skill.setSkillIsMaxed();
                    break;
                case SPEED:
                    princess.addSpeed(1);
                    skill.setSkillIsMaxed();
                    break;
                case INTELLIGENCE:
                    princess.addIntelligence(1);
                    skill.setSkillIsMaxed();
                    break;
                case CHARISMA:
                    princess.addCharisma(1);
                    skill.setSkillIsMaxed();
                    break;
            }
        }
    }
    private void updateSkills(Princess princess, CharacterSkills characterSkills, Skill skill) {
        int princessAttributeInSkill = 0;

        switch (skill.getAttributeEnum()) {
            case STRENGTH:
                princessAttributeInSkill = princess.getAttributes().getStrength();
                break;
            case SPEED:
                princessAttributeInSkill = princess.getAttributes().getSpeed();
                break;
            case INTELLIGENCE:
                princessAttributeInSkill = princess.getAttributes().getIntelligence();
                break;
            case CHARISMA:
                princessAttributeInSkill = princess.getAttributes().getCharisma();
                break;
        }

        characterSkills.updateCharacterSkills(skill.getAttributeEnum(), princessAttributeInSkill);
    }
}
