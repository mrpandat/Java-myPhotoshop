package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MyLayout implements LayoutExample {
    @Override
    public JPanel getContent() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JTabbedPane panelDraw = new JTabbedPane();
        panelDraw.setPreferredSize(new Dimension(200, 100));
        mainPanel.add(panelDraw, BorderLayout.CENTER);

        /****** MENU *****/
        JMenuBar panelTop = new MyMenu(mainPanel, panelDraw);


        /**** PANEL ****/

//        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(200, 200, 200));
        panelTop.setPreferredSize(new Dimension(0, 25));
        mainPanel.add(panelTop, BorderLayout.PAGE_START);


        JPanel panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(50, 0));

        panelRight.setBackground(new Color(255, 0, 0));
        mainPanel.add(panelRight, BorderLayout.LINE_START);

        JPanel panelHistory = new JPanel();
        panelHistory.setPreferredSize(new Dimension(50, 0));

        panelHistory.setBackground(new Color(125, 125, 125));
        mainPanel.add(panelHistory, BorderLayout.LINE_END);


        JPanel panelStatus = new JPanel();
        panelStatus.setPreferredSize(new Dimension(0, 25));

        panelStatus.setBackground(new Color(125, 0, 125));
        mainPanel.add(panelStatus, BorderLayout.PAGE_END);

        return mainPanel;
    }

    @Override
    public String getName() {
        return "MyPhotoshop by treibe_a";
    }
}
