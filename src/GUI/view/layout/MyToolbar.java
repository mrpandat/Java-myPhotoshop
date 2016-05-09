package GUI.view.layout;

import GUI.model.DrawModel;
import GUI.model.MainModel;

import javax.swing.*;
import java.awt.*;


public class MyToolbar extends JPanel {
    public JPanel mainPanel;
    public JPanel drawPanel = new JPanel();
    MyToolbar(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.drawPanel.setLayout(new BorderLayout());
        this.drawPanel.setSize(100,0);
        drawPanel.setVisible(false);

    }

    public void generateButtons() {
        setBounds(60, 400, 220, 30);

        JButton drawbutton = new JButton();
        drawbutton.setText("draw");
        drawbutton.addActionListener(e -> {
            DrawModel.getInstance().setType("pencil");
            drawPanel.setVisible(true);

        });
        add(drawbutton);

        drawbutton = new JButton();
        drawbutton.setText("erase");
        drawbutton.addActionListener(e -> {
            DrawModel.getInstance().setType("erase");
            drawPanel.setVisible(false);
        });
        add(drawbutton);


        setVisible(true);
        generateDrawMenu();
    }

    public void generateDrawMenu() {

        JPanel jPanel = new JPanel();
        JLabel color = new JLabel("Your color");
        JButton drawbutton = new JButton();
        drawbutton.setText("Choose your color");
        drawbutton.addActionListener(e -> {
                    DrawModel.getInstance().setColor(JColorChooser.showDialog(null, "Choose your color", Color.WHITE));
                    jPanel.setBackground(DrawModel.getInstance().getColor());
                }
        );
        drawPanel.add(drawbutton,BorderLayout.NORTH);
        jPanel.add(color);
        drawPanel.add(jPanel,BorderLayout.SOUTH);
        add(drawPanel);
    }


}
