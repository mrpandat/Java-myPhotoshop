package GUI.controller;

import GUI.model.MainModel;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import filter.basic.AllBlue;

public class FiltersController {
    public MainModel model = MainModel.getInstance();

    public void performAllBlue() {
        AllBlue a = new AllBlue();
        model.getImg().setImage(a.perform(this.model.getImg().getImage()));
    }
}
