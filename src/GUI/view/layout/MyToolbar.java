package GUI.view.layout;

import GUI.model.DrawModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class MyToolbar extends JPanel {
    public JPanel mainPanel;
    public JPanel drawPanel = new JPanel();
    public JPanel erasePanel = new JPanel();
    public JPanel textPanel = new JPanel();


    MyToolbar(JPanel mainPanel) {
        this.mainPanel = mainPanel;


        drawPanel.setPreferredSize(new Dimension(200, 500));
        erasePanel.setPreferredSize(new Dimension(200, 500));
        textPanel.setPreferredSize(new Dimension(200, 500));

        erasePanel.setBorder(new EtchedBorder());
        drawPanel.setBorder(new EtchedBorder());
        textPanel.setBorder(new EtchedBorder());

        drawPanel.setVisible(false);
        erasePanel.setVisible(false);
        textPanel.setVisible(false);

    }


    public void generateButtons() {
        setBounds(60, 400, 220, 30);

        //DRAW BUTTON
        JButton drawbutton = new JButton();
        drawbutton.setText("draw");
        drawbutton.addActionListener(e -> {
            reset();

            DrawModel.getInstance().setType("draw");

            drawPanel.setVisible(true);

        });
        add(drawbutton);

        //ERASE BUTTON

        drawbutton = new JButton();
        drawbutton.setText("erase");
        drawbutton.addActionListener(e -> {
            reset();

            DrawModel.getInstance().setType("erase");
            erasePanel.setVisible(true);
        });
        add(drawbutton);

        //TEXT BUTTON

        drawbutton = new JButton();
        drawbutton.setText("text");
        drawbutton.addActionListener(e -> {
            reset();

            DrawModel.getInstance().setType("text");
            textPanel.setVisible(true);
        });
        //add(drawbutton);


        setVisible(true);
        generateDrawMenu();
        generateEraseMenu();
        generateTextMenu();
    }

    public void generateDrawMenu() {
        JPanel jPanel = new JPanel();
        JLabel color = new JLabel("Your color :");
        jPanel.add(color);
        jPanel.setPreferredSize(new Dimension(190, 25));


        drawPanel.add(jPanel);
        drawPanel.add(getColorChooser(jPanel));
        drawPanel.add(new JLabel("Size :"));
        drawPanel.add(getSizeChooser());
        drawPanel.add(new JLabel("Shape :"));
        drawPanel.add(getShapeChooser());

        add(drawPanel);

    }

    public void generateEraseMenu() {
        erasePanel.add(new JLabel("Size :"));
        erasePanel.add(getSizeChooser());
        erasePanel.add(new JLabel("Opacity :"));
        erasePanel.add(getOpacityChooser());
        add(erasePanel);

    }

    public void generateTextMenu() {

        JPanel jPanel = new JPanel();
        JLabel color = new JLabel("Your color :");
        jPanel.add(color);
        jPanel.setPreferredSize(new Dimension(190, 25));

        textPanel.add(jPanel);
        textPanel.add(getColorChooser(jPanel));
        textPanel.add(new JLabel("Size :"));
        textPanel.add(getSizeChooser());

        add(textPanel);

    }

    public JSlider getSizeChooser() {

        JSlider sizeChooser = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        sizeChooser.setMajorTickSpacing(25);
        sizeChooser.setMinorTickSpacing(1);
        sizeChooser.setPaintTicks(true);
        sizeChooser.setPaintLabels(true);
        sizeChooser.addChangeListener(e -> {
            DrawModel.getInstance().setSize(sizeChooser.getValue());
        });

        return sizeChooser;
    }

    public JList getShapeChooser() {
        String label[] = {"Oval", "Square", "Rectangle"};

        JList list = new JList<>(label);
        DefaultListSelectionModel sm = new DefaultListSelectionModel();
        sm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionModel(sm);
        list.addListSelectionListener(e -> DrawModel.getInstance().setShape((String) list.getModel().getElementAt(list.getSelectedIndex())));
        return list;
    }

    public JButton getColorChooser(JPanel jPanel) {
        JButton drawbutton = new JButton();
        drawbutton.setText("Choose your color");
        drawbutton.addActionListener(e -> {
                    DrawModel.getInstance().setColor(JColorChooser.showDialog(null, "Choose your color", Color.WHITE));
                    jPanel.setBackground(DrawModel.getInstance().getColor());
                }
        );
        drawbutton.setPreferredSize(new Dimension(190, 25));

        return drawbutton;
    }

    public JSlider getOpacityChooser() {
        JSlider opacity = new JSlider(JSlider.HORIZONTAL, 1, 250, 125);
        opacity.setMajorTickSpacing(249);
        opacity.setMinorTickSpacing(25);
        opacity.setPaintTicks(true);
        opacity.setPaintLabels(true);
        opacity.addChangeListener(e -> {
            DrawModel.getInstance().setOpacity(opacity.getValue());
        });
        return opacity;
    }

    public void reset() {
        DrawModel.getInstance().reset();
        drawPanel.setVisible(false);
        erasePanel.setVisible(false);
        textPanel.setVisible(false);
    }

}
