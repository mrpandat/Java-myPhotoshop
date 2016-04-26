package GUI.controller;

import GUI.model.MainModel;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import filter.basic.*;

public class FiltersController {
    public MainModel model = MainModel.getInstance();

    public void performAllBlue() {
        AllBlue a = new AllBlue();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();

    }

    public void performVeryLong() {
        VeryLong a = new VeryLong();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();

    }

    public void PerformRotateRight() {
        RotateRight a = new RotateRight();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }
    public void PerformReverse() {
        Reverse a = new Reverse();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }

    public void PerformBinary() {
        Binary a = new Binary();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }
}
