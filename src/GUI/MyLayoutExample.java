package GUI;

import javax.swing.*;
import java.awt.*;

public class MyLayoutExample implements LayoutExample {
    @Override
    public JPanel getContent() {/*
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(185));

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(255,90,90));

        JPanel panelUser = new JPanel();
        panelUser.setBackground(new Color(142,142,142));

        JPanel panelDraw = new JPanel();
        panelDraw.setBackground(new Color(10,200,255));

        JPanel panelToolbox = new JPanel();
        panelToolbox.setBackground(new Color(80,80,80));

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        panelMenu.setLayout(new GridLayout());
        panelUser.setLayout(new BoxLayout(panelUser,BoxLayout.X_AXIS));

        mainPanel.add(panelMenu);
        mainPanel.add(panelUser);
        panelUser.add(panelDraw);
        panelUser.add(panelToolbox);
        panelMenu.setSize(new Dimension(100,100));
        */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(0,0,255));
        panelTop.setPreferredSize(new Dimension(0,50));
        mainPanel.add(panelTop, BorderLayout.PAGE_START);

        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(new Color(0,255,0));
        panelLeft.setPreferredSize(new Dimension(200, 100));
        mainPanel.add(panelLeft, BorderLayout.CENTER);

        JPanel panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(50,0));

        panelRight.setBackground(new Color(255,0,0));
        mainPanel.add(panelRight, BorderLayout.LINE_START);

        return mainPanel;
    }

    @Override
    public String getName() {
        return "Grid Layout";
    }
}
