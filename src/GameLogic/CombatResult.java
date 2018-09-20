package GameLogic;

public class CombatResult {

    private double chanceToWin;
    private String combatText;
    private int result = 0;


    public CombatResult(double chanceToWin, int roll){
        this.chanceToWin = chanceToWin;
        if (roll < chanceToWin) {
            result = 1;
        }
        else if (roll > chanceToWin) {
            result = -1;
        }
        combatText = "Chance to win = " + chanceToWin + "\nRoll = " + roll + "\nResult = " + result;
    }

    public double getResult() {
        return result;
    }

    public String getCombatText() {
        return combatText;
    }
}
