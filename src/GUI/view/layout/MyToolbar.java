package GUI.view.layout;

import GUI.model.DrawModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class MyToolbar extends JPanel {
    public JPanel mainPanel;
    public JPanel drawPanel = new JPanel();
    public JPanel erasePanel = new JPanel();

    MyToolbar(JPanel mainPanel) {
        this.mainPanel = mainPanel;


        drawPanel.setPreferredSize(new Dimension(200,500));
        erasePanel.setPreferredSize(new Dimension(200,500));
        erasePanel.setBorder(new EtchedBorder());
        drawPanel.setBorder(new EtchedBorder());

        drawPanel.setVisible(false);
        erasePanel.setVisible(false);

    }

    public void generateButtons() {
        setBounds(60, 400, 220, 30);

        JButton drawbutton = new JButton();
        drawbutton.setText("draw");
        drawbutton.addActionListener(e -> {
            DrawModel.getInstance().reset();
            DrawModel.getInstance().setType("draw");
            drawPanel.setVisible(true);
            erasePanel.setVisible(false);

        });
        add(drawbutton);

        drawbutton = new JButton();
        drawbutton.setText("erase");
        drawbutton.addActionListener(e -> {
            DrawModel.getInstance().reset();
            DrawModel.getInstance().setType("erase");
            drawPanel.setVisible(false);
            erasePanel.setVisible(true);
        });
        add(drawbutton);


        setVisible(true);
        generateDrawMenu();
        generateEraseMenu();
    }

    public void generateDrawMenu() {
        JPanel jPanel = new JPanel();

        JLabel color = new JLabel("Your color :");
        jPanel.add(color);

        JButton drawbutton = new JButton();
        drawbutton.setText("Choose your color");
        drawbutton.addActionListener(e -> {
                    DrawModel.getInstance().setColor(JColorChooser.showDialog(null, "Choose your color", Color.WHITE));
                    jPanel.setBackground(DrawModel.getInstance().getColor());
                }
        );

        JSlider size = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        size.setMajorTickSpacing(9);
        size.setMinorTickSpacing(1);
        size.setPaintTicks(true);
        size.setPaintLabels(true);
        size.addChangeListener(e -> {
            DrawModel.getInstance().setSize(size.getValue());
        });
        jPanel.setPreferredSize(new Dimension(190,25));
        drawbutton.setPreferredSize(new Dimension(190,25));
        drawPanel.add(jPanel);
        drawPanel.add(drawbutton);
        drawPanel.add(new JLabel("Size :"));
        drawPanel.add(size);

        add(drawPanel);

    }

    public void generateEraseMenu() {

        JSlider size = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        size.setMajorTickSpacing(9);
        size.setMinorTickSpacing(1);
        size.setPaintTicks(true);
        size.setPaintLabels(true);
        size.addChangeListener(e -> {
            DrawModel.getInstance().setSize(size.getValue());
        });

        erasePanel.add(new JLabel("Size :"));
        erasePanel.add(size);

        JSlider opacity = new JSlider(JSlider.HORIZONTAL, 1, 250, 50);
        opacity.setMajorTickSpacing(249);
        opacity.setMinorTickSpacing(25);
        opacity.setPaintTicks(true);
        opacity.setPaintLabels(true);
        opacity.addChangeListener(e -> {
            DrawModel.getInstance().setOpacity(opacity.getValue());
        });


        erasePanel.add(new JLabel("Opacity :"));
        erasePanel.add(opacity);

        add(erasePanel);

    }


}
