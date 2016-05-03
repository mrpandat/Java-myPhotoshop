package GUI.controller.historic;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by jean on 27/04/2016.
 */
public class HistoricController implements Serializable{


    public ArrayList<ActionPanel> actions;


    public int id;
    public int modify = 0;

    public HistoricController(int id, BufferedImage img) {
        this.id = id;
        this.actions = new ArrayList<ActionPanel>();
        this.actions.add(new ActionPanel("Open Image", img));
    }

    public HistoricController(int id, BufferedImage img, ArrayList<ActionPanel> actions) {
        this.id = id;
        this.actions = actions;
        this.actions.add(new ActionPanel("Open Image", img));
    }


    public void print() {
        for (ActionPanel action : this.actions) {
            System.out.println(action.getName());
        }
    }

    public void add(ActionPanel a) {
        if (modify == 0) {
            actions.add(a);
            return;
        }
        this.actions = new ArrayList<ActionPanel>(this.actions.subList(0, this.actions.size() - modify));
        modify = 0;
        this.actions.add(a);

    }

    public void undo() {
        if (modify < this.actions.size() - 1)
            modify++;

    }

    public void redo() {
        if (modify != 0)
            modify--;
    }

    public void buildImages() {
        for (ActionPanel action : this.actions) {
            action.rebuildImages();
        }
    }

    public BufferedImage getHistoricImage() {
        int i = this.actions.size() - modify - 1;
        return this.actions.get(i).getImg();
    }
    public String getLastHistoricName() {
        int i = this.actions.size() - modify - 1;
        return this.actions.get(i).getName();
    }

    public ArrayList<ActionPanel> getActions() {
        return actions;
    }

    public Vector<String> getActionsNames() {
        Vector<String> actionsNames = new Vector<String>();
        int i = this.actions.size() - modify;
        int j = 0;
        while(j <= i && j < this.actions.size()) {
            actionsNames.add(this.actions.get(j).getName());
            j++;
        }
        return actionsNames;
    }


    public BufferedImage getLastimg() {
        return this.actions.get(this.actions.size() - 1).getImg();
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLast() {
        if(modify == 0 || modify == this.actions.size())
            return true;
        return false;
    }

    public boolean isEmpty() {
        return this.actions.isEmpty();
    }

}
