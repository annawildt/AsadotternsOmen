package Story;

public class DialogData {
    String selectedChoice;
    String otherChoice;
    int flag;
    Dialog dialog;
  
  public DialogData(String selectedChoice, String otherChoice, int flag, Dialog dialog){
        this.selectedChoice = selectedChoice;
        this.otherChoice = otherChoice;
        this.flag = flag;
        this.dialog = dialog;
    }

    public String getSelectedChoice() {
        return selectedChoice;
    }

    public String getOtherChoice() {
      return otherChoice;
    }

    public int getFlag() {
        return flag;
    }
  
    public Dialog getDialog(){
        return dialog;
    }

    public void setSelectedChoice(String id) {
      selectedChoice = id;
    }
    public void setDialog (Dialog dialog) {
      this.dialog = dialog;
    }
}
