package GUI.view.layout;

import GUI.controller.MainController;
import GUI.model.MainModel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unchecked")
public class MyLayout {


    public void generateLayout(MainController controller) {
        MainModel model = MainModel.getInstance();
        JTabbedPane panelDraw = model.panelDraw;
        model.mainPanel.setLayout(new BorderLayout());
        panelDraw.setPreferredSize(new Dimension(1000, 600));
        model.mainPanel.add(panelDraw, BorderLayout.CENTER);

        /****** MENU *****/
        MyMenu panelTop = new MyMenu(model.mainPanel, panelDraw);
        panelTop.generateMenus(controller);

        /**** PANEL ****/

        panelTop.setBackground(new Color(110, 110, 110));
        panelTop.setPreferredSize(new Dimension(0, 25));
        model.mainPanel.add(panelTop, BorderLayout.PAGE_START);

        JPanel panelStatus = new JPanel();
        panelStatus.setPreferredSize(new Dimension(0, 25));
        panelStatus.setBackground(new Color(110, 110, 110));

        panelStatus.add(model.statusBar);

        model.mainPanel.add(panelStatus, BorderLayout.PAGE_END);
/*


        JScrollPane scrollBar = new JScrollPane(panelHistoric);
        scrollBar.setVisible(true);
        */

        JPanel panel = new JPanel();

        JList panelHistoric = model.historicList;
        panelHistoric.setSize(new Dimension(200,0));
        panelHistoric.setBackground(new Color(150, 150, 150));
        panelHistoric.setBorder((BorderFactory.createLineBorder(Color.black)));
        panelHistoric.setSelectionBackground(Color.LIGHT_GRAY);
        panelHistoric.setCellRenderer(new MyHistoricRender());
        JScrollPane spane = new JScrollPane(panelHistoric);
        spane.getVerticalScrollBar().setMaximumSize(model.mainPanel.getSize());
        panel.add(spane);
        model.mainPanel.add(spane, BorderLayout.EAST);
        return;
    }

    public JPanel getContent() {
        return MainModel.getInstance().mainPanel;
    }

    public String getName() {
        return "MyPhotoshop by treibe_a";
    }
}
