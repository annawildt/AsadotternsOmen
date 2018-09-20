import Characters.Princess;
import Characters.Skills.Skills;
import Repository.Repository;
import Story.Scenario;
import Story.SceneData;
import Story.StoryConstants;
import TrainingLogic.TrainingLogic;
import UI.Drawable;
import UI.NewWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Princess princess;
    private TrainingLogic trainingLogic;
    private List<Scenario> scenarioList;
    private NewWindow window;
    private boolean doingScenario = true;
    private boolean scenariosRendered;
    private int choice = 0;
    private SceneData currentScene = null;
    private Scenario scenario;
    private ArrayList<Drawable> drawable;
    private boolean running = true;

    public Game(Repository repository, NewWindow window, List<Scenario> scenarios) {
        princess = new Princess(repository, 5, 5, 5, 5, 5, 5, new Skills(new ArrayList<>()));
        trainingLogic = new TrainingLogic();
        this.window = window;
        this.scenarioList = scenarios;
        scenario = scenarioList.get(0);
    }

    public void StartGame() {

        while (running) {
            //do menu
            //do training
        }
    }

    private void doGameLoop() {
        boolean doGameLoop = true;
        doStory();
        while (doGameLoop) {
            checkInput();

            if (!doingScenario && !scenariosRendered) {
                renderScenarios();
            } else if (choice != -1) {
                doStory();

                if (currentScene.getFlag() == StoryConstants.SCENARIO_DONE) {
                    doingScenario = false;
                    scenariosRendered = false;
                }
            }
        }
    }

    private void checkInput() {
        doDelay(200);
        choice = -1;
        if (!doingScenario) {
            checkScenarioChosen();
        } else {
            checkStoryInput();
        }
    }

    private void renderScenarios() {
        drawable = new ArrayList<>();
        for (Scenario scenarioElement : scenarioList) {
            drawable.add(scenarioElement);
        }
        window.render(drawable);
        scenariosRendered = true;
    }

    private void checkStoryInput() {
        if (window.isAlternative1()) {
            choice = 1;
        } else if (window.isAlternative2()) {
            choice = 2;
        } else if (window.isAlternative3()) {
            choice = 0;
        }
    }

    private void checkScenarioChosen() {
        if (window.isAlternative3()) {
            choice = window.getSelectedChoice();
            doingScenario = true;
        }
    }

    private void doDelay(int delay) {
        try {
            Thread thread = new Thread();
            thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doStory() {
        if (isNewScenario()) {
            scenario = scenarioList.get(choice);
        }
        currentScene = scenario.doScenario(princess, choice);
        renderDialog();
        window.resetAlternatives();
    }

    private void renderDialog() {
        drawable = new ArrayList<>();
        drawable.add(currentScene.getDialog());
        window.render(drawable);
    }

    private boolean isNewScenario() {
        return currentScene != null && currentScene.getDialogData().getSelectedChoice() == null;
    }
}
