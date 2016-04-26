package GUI.controller;

import GUI.model.MainModel;
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
    public void PerformVerticalFlip() {
        VerticalFlip a = new VerticalFlip();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();


    }    public void PerformHorizontalFlip() {
        HorizontalFlip a = new HorizontalFlip();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }

    public void PerformBinary() {
        Binary a = new Binary();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }

    public void PerformGrayscale() {
        Grayscale a = new Grayscale();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }

    public void PerformInvert() {
        Invert a = new Invert();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }

    public void PerformRotateLeft() {
        RotateLeft a = new RotateLeft();
        model.setImg(a.perform(this.model.getImg().getImage()));
        model.notifyObservers();
    }
}
