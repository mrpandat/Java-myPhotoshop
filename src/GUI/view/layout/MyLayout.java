package GUI.view.layout;

import GUI.controller.MainController;
import GUI.model.MainModel;

import javax.swing.*;
import java.awt.*;

public class MyLayout {


    public void generateLayout(MainController controller) {
        JTabbedPane panelDraw = MainModel.getInstance().panelDraw;
        MainModel.getInstance().mainPanel.setLayout(new BorderLayout());
        panelDraw.setPreferredSize(new Dimension(200, 100));
        MainModel.getInstance().mainPanel.add(panelDraw, BorderLayout.CENTER);

        /****** MENU *****/
        MyMenu panelTop = new MyMenu( MainModel.getInstance().mainPanel, panelDraw);
        panelTop.generateMenus(controller);

        /**** PANEL ****/

        panelTop.setBackground(new Color(60, 60, 60));
        panelTop.setPreferredSize(new Dimension(0, 25));
        MainModel.getInstance().mainPanel.add(panelTop, BorderLayout.PAGE_START);




        JPanel panelStatus = new JPanel();
        panelStatus.setPreferredSize(new Dimension(0, 25));

        panelStatus.setBackground(new Color(125, 0, 125));
        MainModel.getInstance().mainPanel.add(panelStatus, BorderLayout.PAGE_END);
        return;
    }

    public JPanel getContent() {
        return  MainModel.getInstance().mainPanel;
    }

    public String getName() {
        return "MyPhotoshop by treibe_a";
    }
}
