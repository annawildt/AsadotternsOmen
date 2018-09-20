package Characters.Skills;

import Characters.CharacterConstants;
import Characters.Attributes.AttributeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skills {
    public Map<AttributeEnum, Map<Integer, List<Skill>>> skills = new HashMap<>();

    public Skills(List<Skill> masterList) {
        addAttributeEnums();
        createAttributeList();
        addSkills(masterList);
    }

    private void addAttributeEnums() {
        skills.put(AttributeEnum.STRENGTH, new HashMap<>());
        skills.put(AttributeEnum.INTELLIGENCE, new HashMap<>());
        skills.put(AttributeEnum.SPEED, new HashMap<>());
        skills.put(AttributeEnum.CHARISMA, new HashMap<>());
    }

    private void addSkills(List<Skill> masterList) {
        for (Skill skill : masterList) {
            skills.get(skill.getAttributeEnum()).get(skill.unlockedLevel).add(skill);
        }
    }

    private void createAttributeList() {
        for (int attribute = 0; attribute < CharacterConstants.AMOUNT_OF_ATTRIBUTES; attribute++) {
            Map<Integer, List<Skill>> listMap = new HashMap<>();
            for (int attributeValue = 1; attributeValue <= CharacterConstants.SKILL_MAX_LEVEL; attributeValue++) {
                List<Skill> currentList = new ArrayList<>();
                listMap.put(attributeValue, currentList);
            }
            skills.put(getSkillAttribute(attribute), listMap);
        }
    }

    public AttributeEnum getSkillAttribute(int attribute) {
        AttributeEnum attributeEnum = AttributeEnum.STRENGTH;
        switch (attribute) {
            case 1:
                attributeEnum = AttributeEnum.SPEED;
                break;
            case 2:
                attributeEnum = AttributeEnum.INTELLIGENCE;
                break;
            case 3:
                attributeEnum = AttributeEnum.CHARISMA;
                break;
        }
        return attributeEnum;
    }

    public List<Skill> getSkillWithinRange(AttributeEnum attributeEnum, int minRange, int maxRange) {
        List<Skill> returnThis = new ArrayList<>();

        for (int i = minRange; i <= maxRange; i++) {
            returnThis.addAll(skills.get(attributeEnum).get(i));
        }
        return returnThis;
    }
}
