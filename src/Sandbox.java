import Characters.Attributes.AttributeEnum;
import Characters.Skills.Skill;
import Characters.Enemy;
import Characters.Princess;
import Characters.Skills.Skills;
import GameLogic.Combat;
import GameLogic.CombatResult;
import GameLogic.CombatVariables;
import Repository.Repository;
import TrainingLogic.TrainingLogic;

import java.util.ArrayList;
import java.util.List;

public class Sandbox {
    public static void main(String[] args) {
        String connstr = args[0];
        Repository repository = new Repository(connstr);
      
        Princess princess = new Princess(repository, 1, 2, 3, 4, 5, 6, new Skills(repository.getAllSkills()));

        Skill skill = new Skill("test", "test", AttributeEnum.INTELLIGENCE, 5, 6);

        Enemy enemy = new Enemy("1", 2, 3, 4, 5,4);

        TrainingLogic trainingLogic = new TrainingLogic();
        trainingLogic.trainPrincess(princess, princess.getCharacterSkills(), skill);

        Combat combat = new Combat();

        CombatResult combatResult = combat.calculateCombatResult(new CombatVariables(princess, AttributeEnum.INTELLIGENCE, skill),
                new CombatVariables(enemy, AttributeEnum.INTELLIGENCE));


        System.out.println(combatResult.getCombatText());
    }
}
