package Story;

import Characters.Princess;
import UI.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scenario implements Drawable {
    private Map<String, Scene> scenes;
    private SceneData currentSceneData;
    private String scenarioText;
    private boolean scenarioDone = false;

    public Scenario(Map<String, Scene> scenes, String firstID, String scenarioText){
        this.scenes = scenes;
        Scene firstScene = this.scenes.get(firstID);
        DialogData dialogData = new DialogData(firstScene.getFirstDialogID(), "", firstScene.getFirstDialogFlag(),null);
        currentSceneData = new SceneData(firstID,StoryConstants.AUTO_NEXT_DIALOG, dialogData);
        this.scenarioText = scenarioText;
    }
    public SceneData doScenario(Princess princess,int choice){
        if(currentSceneData.getFlag() != StoryConstants.SCENARIO_DONE) {
            if(currentSceneData.getDialogData().flag == StoryConstants.DONE) {
                if(currentSceneData.getId() != null){
                    String id = currentSceneData.getDialogData().getSelectedChoice();
                    Scene scene = scenes.get(id);
                    currentSceneData = scene.doScene(princess, choice);
                }
            }
            else {
                String id = currentSceneData.getId();
                Scene scene = scenes.get(id);
                currentSceneData = scene.doScene(princess, choice);
            }
        }
        if(currentSceneData.getFlag() == StoryConstants.SCENARIO_DONE){
            scenarioDone = true;
        }
        return currentSceneData;
    }
    public String getText(){
        return scenarioText;
    }
    public boolean getScenarioDone(){
        return scenarioDone;
    }
}
