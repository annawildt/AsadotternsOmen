package Repository;

import Characters.Attributes.AttributeEnum;
import Characters.Enemy;
import Characters.Skills.Skill;
import Story.Dialog;
import Story.Scenario;
import Story.Scene;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private Connection dbconn;

    public Repository(String connstr) {
        try {
            dbconn = DriverManager.getConnection(connstr);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Repository repositoryError");
            throw new RuntimeException(e.getMessage());
        }
    }

    /** SKILLS (dat kills) **/
    public List<Skill> getAllSkills() {
        String stmt = "SELECT * FROM dbo.Skill";

        ArrayList<Skill> skillMasterList = new ArrayList<>();

        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {

            ResultSet res = sth.executeQuery();

            while (res.next()) {
                skillMasterList.add(newSkill(res));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getAllSkills repositoryError");

            throw new RuntimeException(e.getMessage());
        }
        return skillMasterList;
    }

    private Skill newSkill(ResultSet resultSet){
        Skill skill = new Skill("", "", AttributeEnum.STRENGTH, 0, 0);
        AttributeEnum attributeEnum = AttributeEnum.STRENGTH;

        try {
            switch (resultSet.getString(2)) {
                case "SPEED":
                    attributeEnum = AttributeEnum.SPEED;
                    break;
                case "INTELLIGENCE":
                    attributeEnum = AttributeEnum.INTELLIGENCE;
                    break;
                case "CHARISMA":
                    attributeEnum = AttributeEnum.CHARISMA;
                    break;
            }
            skill = new Skill(resultSet.getString(3),
                    resultSet.getString(4),
                    attributeEnum,
                    resultSet.getInt(5),
                    resultSet.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("newSkill repositoryError");
        }
        return skill;
    }

    /** ENEMY **/
    public Enemy getEnemyForScene (String sceneID) {
        String stmt = "SELECT dbo.Enemy.Name, " +
                "dbo.Enemy.Strength, " +
                "dbo.Enemy.Speed, " +
                "dbo.Enemy.Intelligence, " +
                "dbo.Enemy.Charisma,  " +
                "dbo.Enemy.HP " +
                "FROM dbo.Scen " +
                "LEFT JOIN dbo.Enemy ON dbo.Scen.EnemyID = dbo.Enemy.ID " +
                "WHERE dbo.Scen.ID = ? ";

        Enemy enemy = new Enemy("", 0, 0, 0, 0, 0);

        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            sth.setString(1, sceneID);
            ResultSet res = sth.executeQuery();

            if (res.next()) {
                enemy = new Enemy(res.getString(1),
                        res.getInt(2),
                        res.getInt(3),
                        res.getInt(4),
                        res.getInt(5),
                        res.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getEnemyForScene repositoryError");
            throw new RuntimeException(e.getMessage());
        }
        return enemy;
    }

    /** DIALOG, SCENES, AND SCENARIO **/
    public List<Scenario> getAllScenarios() {
        List<Scenario> allScenarios = new ArrayList<>();

        String stmt = "SELECT * FROM dbo.Scenario";

        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            ResultSet res = sth.executeQuery();

            while (res.next()) {
                String scenarioID = res.getString(1);
                allScenarios.add(getScenario(scenarioID));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getAllScenatios repositoryError");
            throw new RuntimeException(e.getMessage());
        }
        return allScenarios;
    }

    public Scenario getScenario(String scenarioID) {
        String stmt = "SELECT * FROM dbo.Scenario WHERE ID=?";
        Map<String, Scene> sceneMap = new HashMap<>();
        String firstSceneID = "";
        String scenarioText = "";

        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            sth.setString(1, scenarioID);
            ResultSet res = sth.executeQuery();

            if (res.next()) {
                firstSceneID = res.getString(2);
                scenarioText = res.getString(3);
                sceneMap = getScenesForScenario(scenarioID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getScenario repositoryError");
            throw new RuntimeException(e.getMessage());
        }
        return new Scenario(sceneMap,firstSceneID,scenarioText);
    }

    private Map<String,Scene> getScenesForScenario(String scenarioID) {
        String stmt = "SELECT * FROM dbo.Scen WHERE ScenarioID = ?";
        Map<String, Scene> sceneMap = new HashMap<>();

        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            sth.setString(1, scenarioID);
            ResultSet res = sth.executeQuery();
            while(res.next()) {
                String sceneID = res.getString(1);
                Scene scene = getScene(sceneID);
                sceneMap.put(sceneID, scene);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("getScenesForScenario repositoryError");
        }
        return sceneMap;
    }

    private Scene getScene(String sceneID) {
        String stmt = "SELECT * FROM dbo.SceneDialog WHERE ScenID = ?";
        Map<String, Dialog> dialogMap = new HashMap<>();
        String firstDialogID = "";
        Enemy enemy = null;

        if(getEnemyForScene(sceneID) != null) {
            enemy = getEnemyForScene(sceneID);
        }

        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            sth.setString(1, sceneID);
            ResultSet res = sth.executeQuery();
            if(res.next()) {
                firstDialogID = res.getString(2);
                Dialog dialog = getDialog(firstDialogID);
                dialogMap.put(firstDialogID, dialog);
                if (checkSceneForEnemy(sceneID)) {
                    enemy = getEnemyForScene(sceneID);
                }
            }
            while (res.next()) {
                String dialogID = res.getString(2);
                Dialog dialog = getDialog(dialogID);
                dialogMap.put(dialogID, getDialog(dialogID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getScene repositoryError");
            throw new RuntimeException(e.getMessage());
        }
        return new Scene(sceneID, enemy, dialogMap, firstDialogID);
    }

    private boolean checkSceneForEnemy(String sceneID) {
        String stmt = "SELECT * FROM dbo.Scen WHERE ID = ?";
        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            sth.setString(1, sceneID);
            ResultSet res = sth.executeQuery();
            if (res.next()) {
                if (res.getInt(2) != 0) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("checkSceneForEnemy repositoryError");
        }
        return false;
    }

    public Dialog getDialog(String dialogID) {
        String stmt = "SELECT * FROM dbo.Dialog WHERE ID = ?";
        Dialog dialog = null;
        try (PreparedStatement sth = dbconn.prepareStatement(stmt)) {
            sth.setString(1, dialogID);

            ResultSet res = sth.executeQuery();
            if(res.next()) {
                String ett = res.getString(2);
                int två = res.getInt(5);
                String tre = res.getString(1);
                String fyra = res.getString(3);
                String fem = res.getString(4);
                String sex = res.getString(6);
                dialog = new Dialog(ett, två, tre, fyra, fem, sex);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getDialog repositoryError");
            throw new RuntimeException(e.getMessage());
        }
        return dialog;
    }
}
