package GUI.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by jean on 27/04/2016.
 */
public class HistoricPanel {
    public ArrayList<ActionPanel> actions;
    public int id;
    public int modify = 0;

    public HistoricPanel(int id, BufferedImage img)
    {
        this.id = id;
        this.actions = new ArrayList<ActionPanel>();
        this.actions.add(new ActionPanel("Open Image", img));
    }


    public void print() {
        System.out.println(System.lineSeparator() + id + " " + modify);
        System.out.println("Current :" + this.actions.get(this.actions.size() - modify -1).getName());
        for (ActionPanel action : this.actions) {
            System.out.println(action.getName());
        }
    }

    public void add(ActionPanel a) {
        if(modify == 0) {
            actions.add(a);
            return;
        }
        this.actions = new ArrayList(this.actions.subList(0, this.actions.size()-modify));
        modify = 0;
        this.actions.add(a);

    }

    public void undo() {
        if( modify < this.actions.size() - 1)
                modify++;

    }

    public void redo() {
        if( modify != 0)
            modify--;
    }

    public BufferedImage getHistoricImage() {
        int i = this.actions.size() - modify -1;
        return this.actions.get(i).getImg();
    }
}
