package GUI.view.layout;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProjectPanel extends JPanel{

    public JPanel projectPanel;
    public ImagePanel imgPanel;

    public ProjectPanel(File file) {
        this.projectPanel = new JPanel();
        this.projectPanel.setLayout(new BorderLayout());

        this.projectPanel.setBackground(new Color(90,90,90));
        this.imgPanel = new ImagePanel(file);
        imgPanel.setSize(new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
        this.projectPanel.add(imgPanel,BorderLayout.CENTER);


        JList panelHistory = new JList();

        panelHistory.setPreferredSize(new Dimension(150, 0));

        panelHistory.setBackground(new Color(125, 125, 125));
        this.projectPanel.add(panelHistory, BorderLayout.LINE_END);


    }
    public JPanel getContent() {
        return projectPanel;
    }

}
