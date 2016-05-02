package GUI.view.layout;


import GUI.controller.panel.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProjectPanel extends JPanel{

    public JPanel projectPanel;
    public ImagePanel imgPanel;

    public ProjectPanel(File file) {
        this.projectPanel = new JPanel();
        this.projectPanel.setLayout(new BorderLayout());

        this.imgPanel = new ImagePanel(file);
        imgPanel.setSize(new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
        this.projectPanel.add(imgPanel,BorderLayout.CENTER);


        JList panelHistory = new JList();

        panelHistory.setPreferredSize(new Dimension(150, 0));

        panelHistory.setBackground(new Color(110, 110, 110));
        this.projectPanel.add(panelHistory, BorderLayout.LINE_END);


    }

    public ProjectPanel(ImagePanel img) {
        this.projectPanel = new JPanel();
        this.projectPanel.setLayout(new BorderLayout());

        this.imgPanel = img;
        imgPanel.buildImage();

        imgPanel.setSize(new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
        this.projectPanel.add(imgPanel,BorderLayout.CENTER);

        JList panelHistory = new JList();

        panelHistory.setPreferredSize(new Dimension(150, 0));

        panelHistory.setBackground(new Color(110, 110, 110));
        this.projectPanel.add(panelHistory, BorderLayout.LINE_END);


    }
    public JPanel getContent() {
        return projectPanel;
    }

}
