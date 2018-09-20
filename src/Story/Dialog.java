package Story;

import Characters.Attributes.AttributeEnum;
import UI.Drawable;

public class Dialog implements Drawable {
    private String text;
    private int flag;
    private String id;
    private String choiceOneID, choiceTwoID;
    private AttributeEnum defaultAtribute;
    private String selectedChoice;
    private String otherChoice;

    public Dialog(String text, int flag, String id, String choiceOneID, String choiceTwoID, String attribute) {
        this.text = text;
        this.flag = flag;
        this.id = id;
        this.choiceOneID = choiceOneID;
        this.choiceTwoID = choiceTwoID;
        if (attribute != null) {
            this.defaultAtribute = convertToEnum(attribute);
        }
        selectedChoice = this.id;
    }

    public DialogData doDialog(int choice) {
        if (flag == StoryConstants.COMBAT || flag == StoryConstants.DONE) {
            selectedChoice = choiceOneID;
            otherChoice = choiceTwoID;
        } else {
            checkAutoOrSetChoices(choice);
        }
        return new DialogData(selectedChoice, otherChoice, flag, this);
    }

    private void checkAutoOrSetChoices(int choice) {
        if (flag == StoryConstants.AUTO_NEXT_DIALOG) {
            selectedChoice = choiceOneID;
        } else {
            if (choice == 1) {
                selectedChoice = choiceOneID;

            } else if (choice == 2) {
                selectedChoice = choiceTwoID;
            }
        }
    }

    private AttributeEnum convertToEnum (String enumString){
        switch (enumString) {
            case "STRENGTH":
                return AttributeEnum.STRENGTH;
            case "INTELLIGENCE":
                return AttributeEnum.INTELLIGENCE;
            case "CHARISMA":
                return AttributeEnum.CHARISMA;
            case "SPEED":
                return AttributeEnum.SPEED;
        }
        return AttributeEnum.STRENGTH;
    }

    public String getText() {
        return text;
    }

    public String getID() {
        return id;
    }

    public int getFlag() {
        return flag;
    }

    public AttributeEnum getDefaultAtribute() {
        return defaultAtribute;
    }
}
