package app.undo_manager;

public class Change {
    int index;
    String removeText;
    String addText;

    public Change(int index, String removeText, String addText){
        this.index = index;
        this.removeText = removeText;
        this.addText = addText;
    }
    
}
