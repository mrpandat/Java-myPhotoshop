package GUI.controller.historic;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

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
        System.out.println(System.lineSeparator() + id + " " + modify);
        System.out.println("Current :" + this.actions.get(this.actions.size() - modify - 1).getName());
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

    public ArrayList<ActionPanel> getActions() {
        return actions;
    }

    public BufferedImage getLastimg() {
        return this.actions.get(this.actions.size() - 1).getImg();
    }

    public void setModify(int modify) {
        this.modify = modify;
    }

    public void setId(int id) {
        this.id = id;
    }

}