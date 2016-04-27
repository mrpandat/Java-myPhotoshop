package GUI.controller;

import GUI.model.ActionPanel;
import GUI.model.MainModel;
import filter.basic.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class FiltersController {
    public MainModel model = MainModel.getInstance();

    public void performAllBlue() {
        AllBlue a = new AllBlue();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();

    }

    public void performVeryLong() {
        VeryLong a = new VeryLong();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();

    }

    public void PerformRotateRight() {
        RotateRight a = new RotateRight();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();
    }
    public void PerformVerticalFlip() {
        VerticalFlip a = new VerticalFlip();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();


    }    public void PerformHorizontalFlip() {
        HorizontalFlip a = new HorizontalFlip();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();
    }

    public void PerformBinary() {
        Binary a = new Binary();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();
    }

    public void PerformGrayscale() {
        Grayscale a = new Grayscale();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();
    }

    public void PerformInvert() {
        Invert a = new Invert();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();
    }

    public void PerformRotateLeft() {
        RotateLeft a = new RotateLeft();
        BufferedImage bi = a.perform(this.model.getImg().getImage());
        model.setImg(bi,new ActionPanel(a.getName(),bi));
        model.notifyObservers();
    }
}
