package Story;

import Characters.Attributes.AttributeEnum;
import Characters.Enemy;
import Characters.Princess;
import GameLogic.Combat;
import GameLogic.CombatResult;
import GameLogic.CombatVariables;

import java.util.Map;

public class Scene {
    String id;
    private DialogData currentDialogData;
    private Map<String,Dialog> dialogs;
    private Enemy enemy;
    private String selectedSceneChoice;
    private int flag;
    private String firstDialogID;
    private CombatResult result = null;
    private boolean combatDone = false;

    public Scene(String sceneID, Enemy enemy, Map<String, Dialog> dialogs, String firstDialogID){
        this.id = sceneID;
        this.enemy = enemy;
        this.dialogs = dialogs;
        this.firstDialogID = firstDialogID;
        this.selectedSceneChoice = this.id;
        currentDialogData = new DialogData(firstDialogID, "", StoryConstants.AUTO_NEXT_DIALOG, dialogs.get(firstDialogID));
    }

    public SceneData doScene(Princess princess, int choice){
        checkCombatOrChoice(choice, princess);
        checkAndUpdateDone();
        return new SceneData(selectedSceneChoice, flag, currentDialogData);
    }

    private void checkAndUpdateDone() {
        if(currentDialogData.getFlag() == StoryConstants.DONE) {
            checkForDoneScenario();
        }else if(combatDone){
            updateDialogDataWhenCombatDone();
        }
    }

    private void checkForDoneScenario() {
        if(currentDialogData.selectedChoice == null){
            flag = StoryConstants.SCENARIO_DONE;
        }
        else {
            selectedSceneChoice = currentDialogData.selectedChoice;
            currentDialogData.setDialog(dialogs.get(currentDialogData.selectedChoice));
        }
    }

    private void updateDialogDataWhenCombatDone() {
        if(result.getResult() == 1){
            currentDialogData.setDialog(dialogs.get(currentDialogData.selectedChoice));
        }else{
            currentDialogData.setSelectedChoice(currentDialogData.getOtherChoice());
            currentDialogData.setDialog(dialogs.get(currentDialogData.selectedChoice));
        }
    }

    private void checkCombatOrChoice(int choice, Princess princess) {
        if(currentDialogData.getFlag() != StoryConstants.DONE){
            currentDialogData = dialogs.get(currentDialogData.getSelectedChoice()).doDialog(choice);
            if(currentDialogData.getFlag() == StoryConstants.COMBAT){
                doCombat(princess);
            }
            else if (currentDialogData.getFlag() == StoryConstants.DO_CHOICE){
                currentDialogData.setDialog(dialogs.get(currentDialogData.getSelectedChoice()));
            }
        }
    }

    private void doCombat(Princess princess) {
        Combat combat = new Combat();
        AttributeEnum attributeEnum = currentDialogData.getDialog().getDefaultAtribute();
        result = combat.calculateCombatResult(new CombatVariables(princess, attributeEnum), new CombatVariables(enemy, attributeEnum));
        combatDone = true;
        flag = StoryConstants.COMBAT_DONE;
    }

    public String getFirstDialogID(){
        return dialogs.get(firstDialogID).getID();
    }

    public int getFirstDialogFlag(){
        return dialogs.get(firstDialogID).getFlag();
    }
}
