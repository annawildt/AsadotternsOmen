import Repository.Repository;
import Story.Scenario;
import Story.Scene;
import UI.NewWindow;

import java.util.List;

public class PrincessKnights {

    public static void main(String[] args) {
        Repository repository = new Repository(args[0]);
        List<Scenario> scenarios = repository.getAllScenarios();
        NewWindow window = new NewWindow();
        window.setVisible(true);
        Game game = new Game(repository, window, scenarios);
        boolean running = true;
        while (running) {
            game.StartGame();
        }
    }
}
