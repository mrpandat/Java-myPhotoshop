package GUI.view.layout;


import GUI.controller.historic.ActionPanel;
import GUI.controller.panel.ImagePanel;
import GUI.model.MainModel;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ProjectPanel extends JPanel{

    public JPanel projectPanel;
    public ImagePanel imgPanel;

    public ProjectPanel(File file) {
        this.projectPanel = new JPanel();
        this.projectPanel.setLayout(new BorderLayout());
        this.imgPanel = new ImagePanel(file);
        initProject();
    }

    public ProjectPanel(ImagePanel img) {
        this.projectPanel = new JPanel();
        this.projectPanel.setLayout(new BorderLayout());
        this.imgPanel = img;
        imgPanel.buildImage();
        initProject();
    }


    public void initProject() {
        imgPanel.setSize(new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
        this.projectPanel.add(imgPanel,BorderLayout.CENTER);

    }

    public JPanel getContent() {
        return projectPanel;
    }

}
